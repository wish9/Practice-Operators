package com.codestates.example.operators;

public class Coffee {
    private String korname;
    private String engname;
    private int price;
    private String coffeeCode;

    public Coffee(String korname, String engname, int price, String coffeeCode) {
        this.korname = korname;
        this.engname = engname;
        this.price = price;
        this.coffeeCode = coffeeCode;
    }

    public String getKorname() {
        return korname;
    }

    public String getEngname() {
        return engname;
    }

    public int getPrice() {
        return price;
    }

    public String getCoffeeCode() {
        return coffeeCode;
    }
}
