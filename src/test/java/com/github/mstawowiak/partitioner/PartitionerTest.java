package com.github.mstawowiak.partitioner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class PartitionerTest {

    static {
        TestDataGenerator.warmUp();
    }

    private static final int NUM_OF_PARTITIONS = 10;
    private static final int INPUT_SIZE = 1_000_000;

    @Test
    public void randomShortInputData() {
        System.out.println("######   RANDOM SHORT INPUT DATA   ######");

        List<String> input = TestDataGenerator.random(INPUT_SIZE,6);
        runSetOfTests(input, NUM_OF_PARTITIONS);
    }

    @Test
    public void randomLongInputData() {
        System.out.println("######   RANDOM LONG INPUT DATA   ######");

        List<String> input = TestDataGenerator.random(INPUT_SIZE,128);
        runSetOfTests(input, NUM_OF_PARTITIONS);
    }

    @Test
    public void subsequentNumbersInputData() {
        System.out.println("######   SUBSEQUENT NUMBERS INPUT DATA   ######");

        List<String> input = TestDataGenerator.subsequentNumbers(INPUT_SIZE);
        runSetOfTests(input, NUM_OF_PARTITIONS);
    }

    private static void runSetOfTests(List<String> input, int numOfPartitions) {
        Arrays.stream(PartitionerType.values())
                .forEach(type -> runTest(type, input, numOfPartitions));
    }

    private static void runTest(PartitionerType type, List<String> input, int numOfPartitions) {
        Map<Integer, Integer> results = distribute(type, input, numOfPartitions);

        writeResults(results);
    }

    private static Map<Integer, Integer> distribute(PartitionerType type, List<String> input, int numOfPartitions) {
        Partitioner partitioner = PartitionerFactory.create(type, numOfPartitions);

        Map<Integer, Integer> results = new HashMap<>(numOfPartitions);

        long start = System.currentTimeMillis();
        for (String value : input) {
            int partition = partitioner.partition(value);
            results.merge(partition, 1, Integer::sum);
        }
        long stop = System.currentTimeMillis();

        System.out.println(type);
        System.out.println("time:      " + (stop - start) + " ms");

        return results;
    }

    private static void writeResults(Map<Integer, Integer> results) {
        System.out.println("tolerance: " + PartitionerStats.tolerance(results.values()));
        System.out.printf("p-value:   %.9f\n", PartitionerStats.chi(results.values()));
        System.out.println();
    }

}
