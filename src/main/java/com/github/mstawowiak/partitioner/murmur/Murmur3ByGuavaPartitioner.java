package com.github.mstawowiak.partitioner.murmur;

import com.github.mstawowiak.partitioner.Partitioner;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import java.nio.charset.Charset;

public class Murmur3ByGuavaPartitioner implements Partitioner {

    private final int numOfPartitions;

    public Murmur3ByGuavaPartitioner(int numOfPartitions) {
        this.numOfPartitions = numOfPartitions;
    }

    public int partition(String value) {
        HashCode hashCode = Hashing.murmur3_128()
                .hashString(value, Charset.defaultCharset());

        return (int) Math.abs(hashCode.asLong() % numOfPartitions);
    }
}
