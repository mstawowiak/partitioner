package com.github.mstawowiak.partitioner.jenkins;

import com.github.mstawowiak.partitioner.Partitioner;

public class JenkinsPartitioner implements Partitioner {

    private final int numOfPartitions;

    public JenkinsPartitioner(int numOfPartitions) {
        this.numOfPartitions = numOfPartitions;
    }

    public int partition(String value) {
        JenkinsHash jenkinsHash = new JenkinsHash();

        long hashcode = jenkinsHash.hash64(value.getBytes());

        return (int) Math.abs(hashcode % numOfPartitions);
    }
}
