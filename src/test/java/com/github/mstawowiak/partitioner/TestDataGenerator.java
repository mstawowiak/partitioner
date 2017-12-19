package com.github.mstawowiak.partitioner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomStringUtils;

class TestDataGenerator {

    public static List<String> random(int size, int length) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            data.add(RandomStringUtils.randomAlphanumeric(length));
        }
        return data;
    }

    public static List<String> subsequentNumbers(int size) {
        List<String> data = new ArrayList<>();
        IntStream.range(0, size)
                .forEach(i -> data.add(Integer.toString(i)));

        return data;
    }

    public static void warmUp() {
        int WARMUP_SIZE = 2_000_000;
        List<String> testList = new ArrayList<>(WARMUP_SIZE);

        IntStream.range(0, WARMUP_SIZE)
                .forEach(i -> testList.add(Integer.toString(i)));
    }

}
