package com.codestates.example;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class HelloReactorExample {
    public static void main(String[] args) throws InterruptedException {
        Flux    //  Reactor Sequence의 시작점, Reactor Sequence가 여러 건의 데이터를 처리함을 의미
                .just("Hello", "Reactor") // 원본 데이터 소스로부터(Original Data Source) 데이터를 emit하는 Publisher의 역할
                .map(message -> message.toUpperCase()) // 발행자로부터 받은 데이터를 처리
                // 여기서는 just() 연산자가 방출하는 영어 문자열을 대문자로 변환한다.
                .publishOn(Schedulers.parallel()) // Reactor Sequence에서 쓰레드 관리자 역할을 하는 Scheduler를 지정
                // publishOn()에서 스케줄러를 지정하면, 다운스트림 스레드가 publishOn() 기준으로 Downstream의 쓰레드가 Scheduler에서 지정한 유형의 쓰레드로 변경된다.
                // 즉, 이 코드에서는 리액터 시퀀스상에서 두 개의 스레드가 실행된다.
                .subscribe(System.out::println, // subscribe()는 총 세 개의 람다 표현식을 매개변수로 가지며, 첫 번째 매개변수는 Publisher가 emit한 데이터를 전달 받아서 처리하는 역할을 한다.
                        error -> System.out.println(error.getMessage()), // 두 번째 매개변수는 Reqctor 시퀀스에서 오류가 발생했을 때 오류를 수신하고 처리하는 역할을 한다.
                        () -> System.out.println("# onComplete")); // 세 번째 매개변수는 리액터 시퀀스가 종료된 후 일부 후처리를 수행하는 역할을 한다.

        Thread.sleep(100L); // Reactor에서 Scheduler로 지정한 쓰레드는 모두 데몬 쓰레드이기 때문에 주 쓰레드인 main 쓰레드가 종료되면 동시에 종료된다.
        // 따라서 데몬 쓰레드 작업이 다 끝나고 main 쓰레드가 끝나도록 0.1초 동작 지연을 넣어준 것이다.
    }
}
