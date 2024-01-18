package msa.orderserver.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import msa.orderserver.domain.OrderEntity;
import msa.orderserver.dto.OrderDto;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
//    private Date createdAt;

    private String orderId;

    public static ResponseOrder from(OrderDto orderDto) {
        return ResponseOrder.builder()
                .productId(orderDto.getProductId())
                .qty(orderDto.getQty())
                .unitPrice(orderDto.getUnitPrice())
                .totalPrice(orderDto.getTotalPrice())
                .build();
    }

    public static ResponseOrder fromEntity(OrderEntity orderEntity) {
        return ResponseOrder.builder()
                .productId(orderEntity.getProductId())
                .qty(orderEntity.getQty())
                .unitPrice(orderEntity.getUnitPrice())
                .totalPrice(orderEntity.getTotalPrice())
                .orderId(orderEntity.getOrderId())
                .build();
    }
}
