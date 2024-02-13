package msa.orderserver.domain;

import javax.persistence.Embeddable;

@Embeddable
public class DeliveryUser {
    private String name;
    private String address;
    private String phoneNumber;
}
