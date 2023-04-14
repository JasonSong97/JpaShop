package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 내장타입이라는 표시
@Getter
public class Address { // Address -> 값의 변경이 불가능한 클래스 만들기

    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    protected Address() {} // JPA 구현 라이브러리가 객체를 생성할 때 리플릭션, 프록시 기술을 사용해야 하기 때문에 기본 생성자 만듬
}
