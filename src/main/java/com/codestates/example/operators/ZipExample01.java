package com.codestates.example.operators;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class ZipExample01 {
    public static void main(String[] args) throws InterruptedException {
        Flux<Long> source1 = Flux.interval(Duration.ofMillis(200L)).take(4); // 0.2초마다 한번씩 데이터를 emit (0,1,2,3)

        Flux<Long> source2 = Flux.interval(Duration.ofMillis(400L)).take(6); // 0.4초마다 한번씩 데이터를 emit (0,1,2,3,4,5)

        Flux
                .zip(source1, source2, (data1, data2) -> data1 + data2)   // 결합시키는데 source2의 남는 데이터(4,5)는 폐기 됨
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(3000L);
    }
}
