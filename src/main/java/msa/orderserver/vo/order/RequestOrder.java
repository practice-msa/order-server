package msa.orderserver.vo.order;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import msa.orderserver.customannotation.NickName;
import msa.orderserver.customannotation.NonNegativeSize;
import msa.orderserver.domain.Delivery;
import msa.orderserver.domain.Order;
import msa.orderserver.domain.OrderStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class RequestOrder {
    @NotNull
    private final Long productId;
    @NonNegativeSize
    private final Integer qty;
    @NonNegativeSize
    private final Integer unitPrice;
    @NickName
    private final String name;
    @NotNull
    private final String address;
    @Pattern(regexp = "^01[0-9]-\\d{4}-\\d{4}$", message = "Invalid phone number format")
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
                .orderStatus(OrderStatus.PROCESSING)
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
