package msa.orderserver.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryUser {
    private String name;
    private String address;
    private String phoneNumber;


}
