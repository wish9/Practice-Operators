package com.codestates.example.schedulers;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SchedulersExample02 {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .range(1, 10)
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe")) // 구독 직후에 어떤 동작을 수행하고 싶다면 doOnSubscribe()에 로직을 작성
                .subscribeOn(Schedulers.boundedElastic())     // Operator 체인의 실행 쓰레드를 Scheduler에서 지정한 쓰레드(boundedElastic)로 변경
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }
}
