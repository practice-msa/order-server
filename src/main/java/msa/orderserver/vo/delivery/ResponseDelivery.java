package msa.orderserver.vo.delivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import msa.orderserver.domain.Delivery;
import msa.orderserver.domain.DeliveryStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ResponseDelivery {
    private final DeliveryStatus deliveryStatus;
    private final Date deliveryStartDate;
    private final Date deliveryCompleteDate;
    private final String deliveryCode;
    private final String deliveryCompany;

    public static ResponseDelivery from(Delivery delivery){
        return ResponseDelivery.builder()
                .deliveryStatus(delivery.getDeliveryStatus())
                .deliveryStartDate(delivery.getDeliveryStartDate())
                .deliveryCompleteDate(delivery.getDeliveryCompleteDate())
                .deliveryCode(delivery.getDeliveryCode())
                .deliveryCompany(delivery.getDeliveryCompany())
                .build();
    }
}
