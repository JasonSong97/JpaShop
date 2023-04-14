package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 내장타입이라는 표시
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
