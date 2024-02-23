package msa.orderserver.service;

import lombok.RequiredArgsConstructor;
import msa.orderserver.domain.Delivery;
import msa.orderserver.repository.DeliveryRepository;
import msa.orderserver.vo.delivery.RequestUpdateDelivery;
import msa.orderserver.vo.delivery.ResponseDelivery;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{
    private final DeliveryRepository deliveryRepository;
    @Override
    public ResponseDelivery getDeliveryStatus(Long id) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(id);
        return optionalDelivery.map(ResponseDelivery::from)
                .orElseThrow(() -> new NoSuchElementException("해당 배송 정보는 없습니다."));
    }

    @Override
    public void updateDeliveryStatus(RequestUpdateDelivery requestUpdateDelivery, Long id) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(id);
        optionalDelivery.ifPresentOrElse(
                delivery -> delivery.updateFromDTO(requestUpdateDelivery),
                () -> { throw new NoSuchElementException("해당 배송 정보는 없습니다."); }
        );
    }
}
