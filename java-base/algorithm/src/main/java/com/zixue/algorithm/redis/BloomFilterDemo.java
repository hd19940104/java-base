package com.zixue.algorithm.redis;


import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器：
 *  可以避免 缓存击穿：缓存没有数据，数据库有数据
 *  可以避免 缓存穿透：缓存没有数据，数据库没有数据
 */

/**
 *
 * 布隆过滤器的原理是，当⼀个元素被加⼊集合时，通过K个散列函数将这个元素映射成⼀个位数组中的K
 * 个点，把它们置为1。检索时，我们只要看看这些点是不是都是1就（⼤约）知道集合中有没有它了：如
 * 果这些点有任何⼀个0，则被检元素⼀定不在；如果都是1，则被检元素很可能在。这就是布隆过滤器的
 * 基本思想。
 * Bloom Filter跟单哈希函数Bit-Map不同之处在于：Bloom Filter使⽤了k个哈希函数，每个字符串跟k个
 * bit对应。从⽽降低了冲突的概率。
 * ****************************************
 * cerberus在收集监控数据的时候, 有的系统的监控项量会很⼤, 需要检查⼀个监控项的名字是否已经
 * 被记录到db过了, 如果没有的话就需要写⼊db.
 * 爬⾍过滤已抓到的url就不再抓，可⽤bloom filter过滤
 * 垃圾邮件过滤。如果⽤哈希表，每存储⼀亿个 email地址，就需要 1.6GB的内存（⽤哈希表实现的
 * 具体办法是将每⼀个 email地址对应成⼀个⼋字节的信息指纹，然后将这些信息指纹存⼊哈希表，
 * 由于哈希表的存储效率⼀般只有 50%，因此⼀个 email地址需要占⽤⼗六个字节。⼀亿个地址⼤约
 * 要 1.6GB，即⼗六亿字节的内存）。因此存贮⼏⼗亿个邮件地址可能需要上百 GB的内存。⽽
 * Bloom Filter只需要哈希表 1/8到 1/4 的⼤⼩就能解决同样的问题。
 *
 *
 */
public class BloomFilterDemo {
    private static int total = 10000000;
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
