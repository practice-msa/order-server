package msa.orderserver.vo;

import msa.orderserver.dto.OrderDto;

public class RequestOrder {
    private final String productId;
    private final Integer qty;
    private final Integer unitPrice;

    public RequestOrder(String productId, Integer qty, Integer unitPrice) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public OrderDto toDto(String userId){
        return OrderDto.builder()
                .productId(productId)
                .qty(qty)
                .unitPrice(unitPrice)
                .userId(userId)
                .build();
    }

}
