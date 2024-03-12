package msa.orderserver.controller;

import lombok.RequiredArgsConstructor;
import msa.orderserver.dto.response.ApiResponse;
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
    ApiResponse<ResponseDelivery> getDelivery(@PathVariable("deliveryId") Long id){
        ResponseDelivery responseDelivery = deliveryService.getDeliveryStatus(id);

        return new ApiResponse<ResponseDelivery>(true,responseDelivery, HttpStatus.FOUND,null);
    }

    @PutMapping("/{deliveryId}")
    ApiResponse<String> updateDelivery(@RequestBody @Valid RequestUpdateDelivery requestUpdateDelivery,
                                          @PathVariable("deliveryId") Long id){
        deliveryService.updateDeliveryStatus(requestUpdateDelivery,id);
        return new ApiResponse<>(true,"성공적으로 처리하였습니다.",HttpStatus.OK,null);
    }
}
