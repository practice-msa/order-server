package msa.orderserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryUser {
    private String name;
    private String address;
    private String phoneNumber;
}
