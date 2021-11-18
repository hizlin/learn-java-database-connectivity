package io.hizlin;

import io.hizlin.snowflake.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

@Slf4j
public class SnowflakeTest {

    /**
     * 测试雪花算法 - 单个线程调用
     */
    @Test
    public void test1() {
        var nodes = 8;
        var snowflake = Snowflake.builder()
                .sequenceRingLength(nodes)
                .build();
        // 事先调用一次预热
        snowflake.next();

        var count = (1 << 12) * 1000 * 2; // 预计两秒生成数量
        var array = new long[count];
        var now = System.currentTimeMillis();
        for (var i = 0; i < count; i++) {
            array[i] = snowflake.next();
        }
        // 停止计时;
        var elapsed = System.currentTimeMillis() - now;

        var mods = new HashMap<Integer, Integer>();
        for (int i = 0; i < nodes; i++) {
            mods.put(i, 0);
        }

        // 检查重复;
        var set = new HashSet<Long>();
        for (var item : array) {
            if (!set.add(item)) {
                throw new RuntimeException("出现重复");
            }

            var index = (int) (item % nodes);
            mods.put(index, mods.get(index) + 1);
        }

        log.info("生成数量: {}, 耗时: {}ms..", set.size(), elapsed);
        log.info("每个节点数量 (求模分配):");
        for (int i = 0; i < nodes; i++) {
            log.info("node{}: {}", i, mods.get(i));
        }
    }

    @Test
    public void test2() {
        var sequenceBits = 14;
        var snowflake = Snowflake.builder()
                .bits(41, 8, sequenceBits)
                .build();
        snowflake.next();

        var count = (1 << sequenceBits) * 1000;
        var array = new long[count];
        var now = System.currentTimeMillis();
        for (var i = 0; i < count; i++) {
            array[i] = snowflake.next();
        }
        // 停止计时;
        var elapsed = System.currentTimeMillis() - now;

        // 检查重复;
        var set = new HashSet<Long>();
        for (var item : array) {
            if (!set.add(item)) {
                throw new RuntimeException("出现重复");
            }
        }

        log.info("生成数量: {}, 耗时: {}ms..", set.size(), elapsed);
    }
}
