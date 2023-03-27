package com.codestates.example.operators;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

@Slf4j
public class FromStreamExample01 {
    public static void main(String[] args) {
        Flux
                .fromStream(Stream.of(200, 300, 400, 500, 600))  //  Stream을 입력으로 전달 받아 emit
                .reduce((a, b) -> a + b)                         // Upstream에서 emit된 두 개의 데이터를 순차적으로 누적 처리
                .subscribe(System.out::println);

        Flux
                .fromIterable(SampleData.coffeeList) // Iterable을 입력으로 전달 받아 emit
                .subscribe(coffee -> log.info("{} : {}", coffee.getKorname(), coffee.getPrice()));
    }
}
