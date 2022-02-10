#!/bin/bash
cat>/mysql-mgr.sql<<EOF
SET SQL_LOG_BIN=0;
CREATE USER 'clusteradmin'@'%' IDENTIFIED WITH sha256_password BY '123456';
GRANT ALL PRIVILEGES ON *.* TO 'clusteradmin'@'%' WITH GRANT OPTION ;

CREATE USER rpl_user@'%' IDENTIFIED WITH sha256_password BY 'password';
GRANT REPLICATION SLAVE ON *.* TO rpl_user@'%';
GRANT BACKUP_ADMIN ON *.* TO rpl_user@'%';
FLUSH PRIVILEGES;
SET SQL_LOG_BIN=1;
CHANGE MASTER TO MASTER_USER='rpl_user', MASTER_PASSWORD='password' FOR CHANNEL 'group_replication_recovery';
EOF

if [ "$1" = 'master' ] ; then
        echo "SET GLOBAL group_replication_bootstrap_group=ON;" >> /mysql-mgr.sql
        echo "START GROUP_REPLICATION;" >> /mysql-mgr.sql
        echo "SET GLOBAL group_replication_bootstrap_group=OFF;" >> /mysql-mgr.sql
elif [ "$1" = 'slave' ]; then
        echo "START GROUP_REPLICATION;" >> /mysql-mgr.sql
fi

echo 'start mysql process'
mysql < /mysql-mgr.sql