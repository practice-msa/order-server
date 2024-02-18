package msa.orderserver.vo.order;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import msa.orderserver.domain.Delivery;
import msa.orderserver.domain.Order;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class RequestOrder {
    private final Long productId;
    private final Integer qty;
    private final Integer unitPrice;
    private final String name;
    private final String address;
    private final String phoneNumber;

    public Order toEntity(String userId, Delivery delivery){
        return Order.builder()
                .productId(productId)
                .qty(qty)
                .unitPrice(unitPrice)
                .totalPrice(unitPrice*qty)
                .userId(userId)
                .orderId(UUID.randomUUID().toString())
                .delivery(delivery)
                .build();
    }


//    public RequestOrder(String productId, Integer qty, Integer unitPrice) {
//        this.productId = productId;
//        this.qty = qty;
//        this.unitPrice = unitPrice;
//    }

//    public OrderDto toDto(String userId){
//        return OrderDto.builder()
//                .productId(productId)
//                .qty(qty)
//                .unitPrice(unitPrice)
//                .userId(userId)
//                .build();
//    }

}
