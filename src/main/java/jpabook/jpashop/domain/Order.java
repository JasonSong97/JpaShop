package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders") // 이름 바꿔주기 order -> orders
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id") // FK라는 표시
    private Member member;

    // JPQL -> SELECT o FROM order o; -> SQL SELECT * FROM order -> (N + 1)

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // cascade -> persist 전파
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간, DB -> order_date

    private OrderStatus status; // 주문상태 [ORDER, STATUS]

    //==연관관계 편의 메소드==// -> 양방향일 떄 좋다
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this); // this = member
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this); // this = orderItem
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
