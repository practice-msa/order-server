package msa.orderserver.vo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import msa.orderserver.domain.OrderStatus;

@Data
@AllArgsConstructor
public class ResponseUpdateOrder {
    private Boolean check;
}
