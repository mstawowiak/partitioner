package com.github.mstawowiak.partitioner.murmur;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import java.nio.charset.Charset;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

public class MurmurHash3ByYonikTest {

    @Test
    public void testMurmur3_x86_32() {
        for (int i = 0; i < 1000; i++) {
            String value = RandomStringUtils.randomAlphanumeric(i);
            Assert.assertTrue(compare3_x86_32(value));
        }
    }

    @Test
    public void testMurmur3_x86_128() {
        for (int i = 0; i < 1000; i++) {
            String value = RandomStringUtils.randomAlphanumeric(i);
            Assert.assertTrue(compare3_x86_128(value));
        }
    }

    public boolean compare3_x86_32(String value) {
        HashCode hashCode = Hashing.murmur3_32()
                .hashString(value, Charset.defaultCharset());
        long guavaReference = hashCode.asInt();

        int yonikHashcode = MurmurHash3ByYonik
                .murmurhash3_x86_32(value.getBytes(), 0, value.length(), 0);

        return guavaReference == yonikHashcode;
    }

    public boolean compare3_x86_128(String value) {
        HashCode hashCode = Hashing.murmur3_128()
                .hashString(value, Charset.defaultCharset());
        long guavaReference = hashCode.asLong();

        MurmurHash3ByYonik.LongPair out = new MurmurHash3ByYonik.LongPair();
        MurmurHash3ByYonik.murmurhash3_x64_128(value.getBytes(), 0, value.length(), 0, out);
        long yonikHashcode = out.val1;

        return guavaReference == yonikHashcode;
    }
}
