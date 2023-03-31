package com.codestates.example.operators;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class DoOnNextExample { // emit되는 데이터의 유효성 검증
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.coffeeList)
                .doOnNext(coffee -> validateCoffee(coffee))
                .subscribe(data -> log.info("{} : {}", data.getKorname(), data.getPrice()));
    }

    private static void validateCoffee(Coffee coffee) {
        if (coffee == null) {
            throw new RuntimeException("Not found coffee");
        }
        // 유효성 검증에 필요한 로직을 필요한 만큼 추가할 수 있다.
    }
}
