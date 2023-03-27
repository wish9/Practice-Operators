package com.codestates.example.schedulers;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SchedulersExample03 {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .range(1, 10)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe"))

                .publishOn(Schedulers.parallel())  // 필터 기능 쓰레드 변경
                .filter(n -> n % 2 == 0)
                .doOnNext(data -> log.info("# filter doOnNext")) // doOnNext()는 doOnNext() 바로 앞에 위치한 Operator가 실행될 때, 트리거 되는 Operator

                .publishOn(Schedulers.parallel())    // map Operator 쓰레드 변경
                .map(n -> n * 2)
                .doOnNext(data -> log.info("# map doOnNext"))

                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }
}
