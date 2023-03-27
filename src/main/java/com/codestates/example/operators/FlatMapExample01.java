package com.codestates.example.operators;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class FlatMapExample01 {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .range(2, 6)         // 2부터 6개 호출 (즉, 2~7 호출)
                .flatMap(dan -> Flux
                        .range(1, 9)  // 1부터 9개 호출 (1~9 호출)
                        .publishOn(Schedulers.parallel())   // flatMap() 내부의 Inner Sequence를 처리할 쓰레드를 할당 // 여러 개의 쓰레드가 비동기적으로 동작하게 만드는 것
                        .map(num -> dan + " x " + num + " = " + dan * num)) // 구구단 형식으로 문자열 구성
                .subscribe(log::info);

        Thread.sleep(100L);
    }
}