package msa.orderserver.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
public enum OrderStatus {
    PROCESSING(0),
    COMPLETED(1),
    CANCELLED(2);

    @Setter
    private int value;

    OrderStatus(int value) {
        this.value = value;
    }

}
