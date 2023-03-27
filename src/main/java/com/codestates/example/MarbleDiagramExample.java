package com.codestates.example;

import reactor.core.publisher.Flux;

public class MarbleDiagramExample {
    public static void main(String[] args) {
        Flux
                .just("Green-Circle", "Orange-Circle", "Blue-Circle")   // 세 개의 문자열을 emit
                .map(figure -> figure.replace("Circle", "Diamond"))   // Circle -> Diamond
                .subscribe(System.out::println);   // map() Operator 내부에서 변환된 문자열을 출력
    }
}