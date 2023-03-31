package com.codestates.example.operators;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class ErrorExample {
    public static void main(String[] args) {
        Mono.justOrEmpty(findVerifiedCoffee())  // justOrEmpty() Operator는 파라미터로 전달되는 데이터소스가 null 이어도 에러가 발생하지 않는다.
                .switchIfEmpty(Mono.error(new RuntimeException("Not found coffee")))  // switchIfEmpty() => 만약 null이면 괄호안 수행
                .subscribe(
                        data -> log.info("{} : {}", data.getKorname(), data.getPrice()), // 만약 정상적인 coffee 객체를 받으면, 받은 데이터 출력
                        error -> log.error("# onError: {}", error.getMessage()));  // error 객체를 전달 받아서 에러 메시지를 출력
    }

    private static Coffee findVerifiedCoffee() {
        // 데이터베이스에서 Coffee 정보를 조회할 때 null값이 리턴됐다고 가정
        return null;
    }
}
