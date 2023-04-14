package jpabook.jpashop.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") // DB입장에서 구분을 해야하기 때문에
@Getter @Setter
public class Book extends Item{

    private String author;
    private String isbn;
}
