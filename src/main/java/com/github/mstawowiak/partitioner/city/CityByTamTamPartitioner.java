package com.github.mstawowiak.partitioner.city;

import com.github.mstawowiak.partitioner.Partitioner;

public class CityByTamTamPartitioner implements Partitioner {

    private final int numOfPartitions;

    public CityByTamTamPartitioner(int numOfPartitions) {
        this.numOfPartitions = numOfPartitions;
    }

    public int partition(String value) {
        long[] hashcode = CityHashByTamTam
                .cityHash128(value.getBytes(), 0, value.length());

        return (int) Math.abs(hashcode[0] % numOfPartitions);
    }
}
