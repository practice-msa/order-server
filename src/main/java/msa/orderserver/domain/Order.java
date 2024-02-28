package msa.orderserver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import msa.orderserver.vo.order.RequestUpdateOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false)
    private Integer unitPrice;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique = true)
    private String orderId;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public void update(RequestUpdateOrder requestUpdateOrder){
        this.setQty(requestUpdateOrder.getQty());
        this.getDelivery().getDeliveryUser().setName(requestUpdateOrder.getName());
        this.getDelivery().getDeliveryUser().setAddress(requestUpdateOrder.getAddress());
        this.getDelivery().getDeliveryUser().setPhoneNumber(requestUpdateOrder.getPhoneNumber());
    }

}
