package com.github.mstawowiak.partitioner;

import com.github.mstawowiak.partitioner.city.CityByOpenHFTPartitioner;
import com.github.mstawowiak.partitioner.city.CityByTamTamPartitioner;
import com.github.mstawowiak.partitioner.jenkins.JenkinsPartitioner;
import com.github.mstawowiak.partitioner.murmur.Murmur3ByGreenRobotPartitioner;
import com.github.mstawowiak.partitioner.murmur.Murmur3ByGuavaPartitioner;
import com.github.mstawowiak.partitioner.murmur.Murmur3ByYonikPartitioner;
import com.google.common.base.Preconditions;

public final class PartitionerFactory {

    public static Partitioner create(PartitionerType type, int numOfPartitions) {
        Preconditions.checkNotNull(numOfPartitions);
        Preconditions.checkArgument(numOfPartitions > 0);

        switch (type) {
            case MURMUR3_GUAVA:
                return new Murmur3ByGuavaPartitioner(numOfPartitions);
            case MURMUR3_GREENROBOT:
                return new Murmur3ByGreenRobotPartitioner(numOfPartitions);
            case MURMUR3_YONIK:
                return new Murmur3ByYonikPartitioner(numOfPartitions);
            case CITY_TAMTAM:
                return new CityByTamTamPartitioner(numOfPartitions);
            case CITY_OPENHFT:
                return new CityByOpenHFTPartitioner(numOfPartitions);
            case JENKINS:
                return new JenkinsPartitioner(numOfPartitions);
            default:
                throw new IllegalArgumentException(String.format("Partition type '%s' is not supported", type));
        }
    }

    private PartitionerFactory() {
    }
}
