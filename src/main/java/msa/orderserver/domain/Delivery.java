package msa.orderserver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import msa.orderserver.vo.delivery.RequestUpdateDelivery;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="delivery")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // enum으로 배송 상태
    private DeliveryStatus deliveryStatus;

    // 배송 시작일
    private Date deliveryStartDate;

    // 배송 완료일
    private Date deliveryCompleteDate;

    // 수령자 정보
    @Embedded
    private DeliveryUser deliveryUser;

    // 배송 옵션

    // 비용
    private Integer price;

    // 업체
    private String deliveryCompany;

    // 배송 추적 번호
    private String deliveryCode;

    // 주문 아이디
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "delivery")
    private Order order;

    public void updateFromDTO(RequestUpdateDelivery dto) {
        this.deliveryStatus = dto.getDeliveryStatus();
        this.deliveryStartDate = dto.getDeliveryStartDate();
        this.deliveryCompleteDate = dto.getDeliveryCompleteDate();
        this.deliveryCompany = dto.getDeliveryCompany();
        this.deliveryCode = dto.getDeliveryCode();
    }
}
