package com.jm.reflection.annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ApiController
{
    private static final Random random = new Random();

    @Trackable
    @GetMapping(path = "/hello")
    public String waitARandomThenSayHello() throws InterruptedException
    {
        int millisecondsToSleep = random.nextInt(35);
        Thread.sleep(millisecondsToSleep);
        return "Hello !!, get a random " + millisecondsToSleep;
    }

    @Trackable
    @GetMapping(path = "/hello/{millisecondsToSleep}")
    public String waitRequestedThenSayHello(@PathVariable Integer millisecondsToSleep) throws InterruptedException
    {
        Thread.sleep(millisecondsToSleep);
        return "Hello !!, get a random " + millisecondsToSleep;
    }
}
