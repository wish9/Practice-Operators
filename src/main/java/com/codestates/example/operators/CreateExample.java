package com.codestates.example.operators;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class CreateExample {
    private static List<Integer> source= Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19);

    public static void main(String[] args) {
        Flux.create((FluxSink<Integer> sink) -> {   // FluxSink = 데이터 emit 등의 기능을 개발자가 직접 코드를 구현해서 처리함을 의미
            sink.onRequest(n -> { // Subscriber에서 데이터를 요청하면 onRequest()의 파라미터인 람다 표현식이 실행된다.
                for (int i = 0; i < source.size(); i++) {
                    sink.next(source.get(i));   // next() 메서드로 List source의 원소를 emit
                }
                sink.complete();    // Sequence를 종료
            });

            sink.onDispose(() -> log.info("# clean up")); // onDispose() = Sequence가 완전히 종료되기 직전에 호출되며, sequence 종료 직전 후처리 작업을 할 수 있다.
        }).subscribe(data -> log.info("# onNext: {}", data));
    }
}