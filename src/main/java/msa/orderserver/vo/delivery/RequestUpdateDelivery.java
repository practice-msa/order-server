package msa.orderserver.vo.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import msa.orderserver.domain.Delivery;
import msa.orderserver.domain.DeliveryStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@AllArgsConstructor
public class RequestUpdateDelivery {
    @NotNull
    private final DeliveryStatus deliveryStatus;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date format. Date should be in the format yyyy-MM-dd")
    private final Date deliveryStartDate;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date format. Date should be in the format yyyy-MM-dd")
    private final Date deliveryCompleteDate;
    @NotNull
    private final String deliveryCompany;
    @NotNull
    private final String deliveryCode;

}
