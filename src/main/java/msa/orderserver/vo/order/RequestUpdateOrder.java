package msa.orderserver.vo.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestUpdateOrder {
    private final Integer qty;
    private final String name;
    private final String address;
    private final String phoneNumber;
}
