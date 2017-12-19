package com.github.mstawowiak.partitioner.murmur;

import com.github.mstawowiak.partitioner.Partitioner;
import de.greenrobot.common.hash.Murmur3F;

public class Murmur3ByGreenRobotPartitioner implements Partitioner {

    private final int numOfPartitions;

    public Murmur3ByGreenRobotPartitioner(int numOfPartitions) {
        this.numOfPartitions = numOfPartitions;
    }

    public int partition(String value) {
        Murmur3F murmur = new Murmur3F();
        murmur.update(value.getBytes());
        long hash = murmur.getValue();

        return (int) Math.abs(hash % numOfPartitions);
    }

}