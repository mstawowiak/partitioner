package com.github.mstawowiak.partitioner.murmur;

import com.github.mstawowiak.partitioner.Partitioner;

public class Murmur3ByYonikPartitioner implements Partitioner {

    private final int numOfPartitions;

    public Murmur3ByYonikPartitioner(int numOfPartitions) {
        this.numOfPartitions = numOfPartitions;
    }

    public int partition(String value) {
        MurmurHash3ByYonik.LongPair out = new MurmurHash3ByYonik.LongPair();
        MurmurHash3ByYonik.murmurhash3_x64_128(value.getBytes(), 0, value.length(), 0, out);

        return (int) Math.abs(out.val1 % numOfPartitions);
    }
}