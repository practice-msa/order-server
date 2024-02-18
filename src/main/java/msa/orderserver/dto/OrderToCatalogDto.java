//package msa.orderserver.dto;
//
//import lombok.Builder;
//import lombok.Data;
//import msa.orderserver.domain.Order;
//
//import java.io.Serializable;
//
//@Data
//@Builder
//public class OrderDto implements Serializable {
//    private String productId;
//    private Integer qty;
//    private Integer unitPrice;
//    private Integer totalPrice;
//
//    private String orderId;
//    private String userId;
//
//    public static OrderDto from(Order orderEntity) {
//        return OrderDto.builder()
//                .productId(orderEntity.getProductId())
//                .qty(orderEntity.getQty())
//                .unitPrice(orderEntity.getUnitPrice())
//                .totalPrice(orderEntity.getTotalPrice())
//                .orderId(orderEntity.getOrderId())
//                .userId(orderEntity.getUserId())
//                .build();
//    }
//
//
//    public Order toEntity(OrderDto orderDto) {
//        return Order.builder()
//                .orderId(orderDto.getOrderId())
//                .qty(orderDto.getQty())
//                .unitPrice(orderDto.getUnitPrice())
//                .totalPrice(orderDto.getTotalPrice())
//                .productId(orderDto.getProductId())
//                .userId(orderDto.getUserId())
//                .build();
//    }
//}
