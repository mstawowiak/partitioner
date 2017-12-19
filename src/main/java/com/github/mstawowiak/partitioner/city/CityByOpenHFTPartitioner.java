package com.github.mstawowiak.partitioner.city;

import com.github.mstawowiak.partitioner.Partitioner;
import net.openhft.chronicle.algo.hashing.LongHashFunction;

public class CityByOpenHFTPartitioner implements Partitioner {

    private final int numOfPartitions;

    public CityByOpenHFTPartitioner(int numOfPartitions) {
        this.numOfPartitions = numOfPartitions;
    }

    public int partition(String value) {
        long hashcode = LongHashFunction.city_1_1()
                .hashBytes(value.getBytes());

        return (int) Math.abs(hashcode % numOfPartitions);
    }

}
