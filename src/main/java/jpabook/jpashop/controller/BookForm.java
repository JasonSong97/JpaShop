package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    private Long id; // 상품수정이 있어서 id 값이 있어야 한다

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

}
