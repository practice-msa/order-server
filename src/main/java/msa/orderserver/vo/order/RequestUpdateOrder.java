package msa.orderserver.vo.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import msa.orderserver.customannotation.NickName;
import msa.orderserver.customannotation.NonNegativeSize;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class RequestUpdateOrder {
    @NonNegativeSize
    private final Integer qty;
    @NickName
    private final String name;
    @NotNull
    private final String address;
    @Pattern(regexp = "^01[0-9]-\\d{4}-\\d{4}$", message = "Invalid phone number format")
    private final String phoneNumber;
}
