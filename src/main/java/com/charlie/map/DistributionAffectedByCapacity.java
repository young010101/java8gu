package com.charlie.map;

import com.charlie.sort.Utils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: charlie
 * @CreateTime: Created in 2025/2/11 15:48
 * @Description: HashMap种数组容量对 key 分布的影响
 */
public class DistributionAffectedByCapacity {

    public static void main(String[] args) {
        System.out.println(hashtableGrowRule(10));
       int[] array = Utils.randomArray(1000); // 足够随机
//         int[] array = Utils.lowSameArray(1000);
//        int[] array = Utils.evenArray(1000);
        System.out.println(Arrays.toString(array));
        int[] sizes = {11, 16, 23};
        printHashResult(array, sizes);
    }

    public static void printHashResult(int[] array, int[] sizes) {
        List<Map<Integer, AtomicInteger>> maps = new ArrayList<>();
        for (int size : sizes) {
            maps.add(getMap(size));
        }
        for (int hash : array) {
            for (int j = 0; j < sizes.length; j++) {
                maps.get(j).get(hash % sizes[j]).incrementAndGet();
            }
        }
        for (Map<Integer, AtomicInteger> map : maps) {
            System.out.printf("size:[%d] %s%n", map.size(), map);
        }
    }

    private static HashMap<Integer, AtomicInteger> getMap(int size) {
        HashMap<Integer, AtomicInteger> result = new HashMap<>();
        for (int i = 0; i < size; i++) {
            result.put(i, new AtomicInteger());
        }
        return result;
    }

    private static List<Integer> hashtableGrowRule(int n) {
        List<Integer> list = new ArrayList<>();
        int init = 0;
        list.add(init);
        if (n >= 1) {
            init = 11;
            list.add(init);
        }
        for (int i = 1; i < n; i++) {
            init = (init << 1) + 1;
            list.add(init);
        }
        return list;
    }
}
