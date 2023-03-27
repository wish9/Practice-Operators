package com.codestates.example.operators;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ConcatExample {
    public static void main(String[] args) {
        Flux
                .concat(Flux.just("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                        Flux.just("Saturday", "Sunday"))
                .subscribe(System.out::println);

        Flux
                .concat(Flux.fromIterable(SampleData.salesOfCafeA),
                        Flux.fromIterable(SampleData.salesOfCafeB),
                        Flux.fromIterable(SampleData.salesOfCafeC))
                .reduce((a, b) -> a + b)
                .subscribe(data -> log.info("# total sales: {}", data));
    }
}
