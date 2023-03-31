package com.codestates.example.operators;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Collectors;

@Slf4j
public class TimeoutRetryExample { // 일정 시간 내에 데이터가 emit 되지 않으면 다시 시도하는 예제
    public static void main(String[] args) throws InterruptedException {
        getCoffees()
                .collect(Collectors.toSet())   // emit된 데이터를 Set<Coffee>으로 변환 // timeout이 되기전에 이미 emit된 데이터가 있으므로 재구독 후, 다시 emit된 데이터에 동일한 데이터가 있으므로 중복을 제거하기 위함
                .subscribe(bookSet -> bookSet
                        .stream()
                        .forEach(data ->
                                log.info("{} : {}", data.getKorname(), data.getPrice())));

        Thread.sleep(12000);
    }

    private static Flux<Coffee> getCoffees() {
        final int[] count = {0};
        return Flux
                .fromIterable(SampleData.coffeeList)
                .delayElements(Duration.ofMillis(500)) // 입력으로 주어진 시간만큼 각각의 데이터 emit을 지연시키는 Operator (0.5초 지연)
                .map(coffee -> {
                    try {
                        count[0]++;
                        if (count[0] == 4) { // 3번째 emit일 때
                            Thread.sleep(2000); // 2초 지연
                        }
                    } catch (InterruptedException e) {
                    }

                    return coffee;
                })
                .timeout(Duration.ofSeconds(2))   // 2초안에 데이터가 emit되지 않으면 onError Signal 이벤트 발생
                .retry(1)     // 에러나면 1회 재구독을 해서 Sequence를 다시 시작 (처음부터 한번 더 다시하라는 명령)
                .doOnNext(coffee -> log.info("# getCoffees > doOnNext: {}, {}",
                        coffee.getKorname(), coffee.getPrice()));
    }
}