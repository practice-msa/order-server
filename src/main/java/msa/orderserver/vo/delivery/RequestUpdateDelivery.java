package msa.orderserver.vo.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import msa.orderserver.domain.Delivery;
import msa.orderserver.domain.DeliveryStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class RequestUpdateDelivery {
    private final DeliveryStatus deliveryStatus;
    private final Date deliveryStartDate;
    private final Date deliveryCompleteDate;
    private final String deliveryCompany;
    private final String deliveryCode;

}
