package com.carlnk.args;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgTest {

    //-l -p 8080 -d /usr/logs
    //[-l], [-p, 8080], [-d, /usr/logs]
    //{-l: [], -p: [8080], -d: [/usr/logs]}

    //Single Option:
    // TODO: 2022/7/23 Bool -l
    // TODO: 2022/7/23 Integer -p 8080
    // TODO: 2022/7/23 String -d /usr/logs
    // TODO: 2022/7/23 Multiple Options: -l -p 8080 -d /usr/logs
    //Sad path
    // TODO: 2022/7/23 Bool -l t / -l t f
    // TODO: 2022/7/23 Integer -p / -p 8080 8081
    // TODO: 2022/7/23 String -d / -d /usr/logs /usr/vars
    //Default value
    // TODO: 2022/7/23 Bool false
    // TODO: 2022/7/23 Integer 0
    // TODO: 2022/7/23 String ""

    @Test
    public void should_example_1() {
        Options options = Args.parse(Option.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertTrue(options.logging());
        assertEquals(options.port());
    }

    @Test
    public void should_example_2() {
        Options options = Args.parse(ListOption.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");
        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.group());
        assertArrayEquals(new int[]{1, 2, -3, 5}, options.demicals());
    }

    static record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String directory) {
    }

    static record ListOptions(@Option("g") String group, @Option("d") int[] decimals) {
    }

}
