package com.github.mstawowiak.partitioner;

import com.google.common.math.Stats;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.stream.IntStream;
import org.apache.commons.math3.stat.inference.ChiSquareTest;

class PartitionerStats {

    public static double chi( Collection<Integer> values) {
        int numOfValues = values.size();
        double mean = Stats.of(values).mean();

        double[] expected = new double[numOfValues];

        IntStream.range(0, numOfValues)
                .forEach(i -> expected[i] = mean);

        ChiSquareTest t = new ChiSquareTest();
        return t.chiSquareTest(expected, values.stream().mapToLong(l -> l).toArray());
    }

    public static BigDecimal tolerance(Collection<Integer> values) {
        int numOfValues = values.size();
        double mean = Stats.of(values).mean();

        BigDecimal sum = BigDecimal.ZERO;
        for (Integer value : values) {
            BigDecimal tolerance = singleTolerance(value, mean);
            sum = sum.add(tolerance);
        }
        return sum.divide(new BigDecimal(numOfValues), 10, RoundingMode.HALF_UP);
    }

    public static BigDecimal singleTolerance(int value, double mean) {
        return BigDecimal.valueOf(value - mean).abs()
                .divide(BigDecimal.valueOf(mean), 10, RoundingMode.HALF_UP);
    }

}
