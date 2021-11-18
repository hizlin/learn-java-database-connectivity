
## Snowflake

- Source  
  https://github.com/twitter-archive/snowflake/tree/snowflake-2010

- 结构

  | sign | timestamp | worker | sequence |
  | --- | --- | --- | --- |
  | 1 bit | 41 bits | 10 bits | 12 bits |
  > timestamp: [0, 0x200_0000_0000); 单位: 毫秒; 可用约 69.7 年;  
  > worker: [0, 1024)  
  > sequence: [0,4096)  
  > 单个 worker 每秒并发 400W+  

### UidGenerator

- Source  
  https://github.com/baidu/uid-generator

- Readme  
  https://github.com/baidu/uid-generator/blob/master/README.zh_cn.md

- 结构

  | sign | delta seconds | worker | sequence |
  | --- | --- | --- | --- |
  | 1 bit | 28 bits | 22 bits | 13 bits |
  > delta seconds: [0, 0x1000_0000); 可用约 8.5 年;  
  > worker: [0, 0x40_0000);  
  > sequence: [0, 0x2000=8192);  

### Leaf

- Source  
https://github.com/Meituan-Dianping/Leaf

- Readme  
https://github.com/Meituan-Dianping/Leaf/blob/master/README_CN.md