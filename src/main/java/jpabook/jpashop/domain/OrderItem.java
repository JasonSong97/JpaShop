package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * protected OrderItem() {
 *      NoArgsConstructor(access = AccessLevel.PROTECTED)와 같다
 *      생성 메소드를 위해서, 생성자가 있으면 유지보수 하기 어렵지만
 *      생성 메소드가 있으면, 항상 형태를 유지하고, 유지보수하기 쉽다. 한 곳만 수정하면 되기 때문에
 * }
 */
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문가격

    private int count; // 주문 수량

    //==생성 메소드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();

        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); // 재고에서 까주기

        return orderItem;
    }

    //==비지니스 로직==//
    public void cancel() {
        getItem().addStock(count); // 재고를 다시 주문 수량만큼 늘리기
    }

    //==조회 로직==//

    /**
     * 주문상품 전체 가격 조회
     */
    public int getTotalPrice() { // 주문 가격 * 주문 수량
        return getOrderPrice() * getCount();
    }
}
