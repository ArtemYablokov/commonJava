package com.yablokovs.streamApi;

import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class StreamExampleTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void multiplyAllBase1Test() {
        Assertions.assertEquals(24, StreamExample.multiplyStreamEntries(Stream.of(1, 2, 3, 4)));
    }
}