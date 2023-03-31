package com.codestates.example.operators;

import reactor.core.publisher.Flux;

import java.util.stream.Stream;

public class LogExample {
    public static void main(String[] args) {
        Flux
                .fromStream(Stream.of(200, 300, 400, 500, 600))
                .log()
                .reduce((a, b) -> a + b)
                .log()
                .subscribe(System.out::println);
    }
}
