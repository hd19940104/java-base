package com.zixue.algorithm.redis;


import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器：
 *  可以避免 缓存击穿：缓存没有数据，数据库有数据
 *  可以避免 缓存穿透：缓存没有数据，数据库没有数据
 */
public class BloomFilterDemo {
    private static int total = 1000000;
//    private static BloomFilter<Integer> bf =
//            BloomFilter.create(Funnels.integerFunnel(), total);
 private static BloomFilter<Integer> bf =
BloomFilter.create(Funnels.integerFunnel(), total, 0.001);
    public static void main(String[] args) {
// 初始化1000000条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put(i);
        }
// 匹配已在过滤器中的值，是否有匹配不上的
        for (int i = 0; i < total; i++) {
            if (!bf.mightContain(i)) {
                System.out.println("有坏⼈逃脱了~~~");
            }
        }
// 匹配不在过滤器中的10000个值，有多少匹配出来
        int count = 0;
        for (int i = total; i < total + 1000000; i++) {
            if (bf.mightContain(i)) {
                count++;
            }
        }
        System.out.println("误伤的数量：" + count);
    }
}
