package msa.orderserver.controller;

import lombok.RequiredArgsConstructor;
import msa.orderserver.service.DeliveryService;
import msa.orderserver.vo.delivery.RequestUpdateDelivery;
import msa.orderserver.vo.delivery.ResponseDelivery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.PUT;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping("/{deliveryId}")
    ResponseEntity<ResponseDelivery> getDelivery(@PathVariable("deliveryId") Long id){
        ResponseDelivery responseDelivery = deliveryService.getDeliveryStatus(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(responseDelivery);
    }

    @PutMapping("/{deliveryId}")
    ResponseEntity<String> updateDelivery(@RequestBody @Valid RequestUpdateDelivery requestUpdateDelivery,
                                          @PathVariable("deliveryId") Long id){
        deliveryService.updateDeliveryStatus(requestUpdateDelivery,id);
        return ResponseEntity.status(HttpStatus.OK).body("성공적 처리");
    }
}
