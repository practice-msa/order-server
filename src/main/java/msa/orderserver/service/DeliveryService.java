package msa.orderserver.service;

import msa.orderserver.vo.delivery.RequestUpdateDelivery;
import msa.orderserver.vo.delivery.ResponseDelivery;

public interface DeliveryService {
    // 배송 정보 확인(배송 아이디로)
    ResponseDelivery getDeliveryStatus(Long id);

    // 배송 정보 변경(예를들어 출고 완료, 출고 중 등등)
    void updateDeliveryStatus(RequestUpdateDelivery requestUpdateDelivery, Long id);
}
