package msa.orderserver.controller;

import lombok.RequiredArgsConstructor;
import msa.orderserver.domain.Order;
//import msa.orderserver.messagequeue.KafkaProducer;
//import msa.orderserver.messagequeue.OrderProducer;
import msa.orderserver.dto.OrderToCatalogDto;
import msa.orderserver.service.OrderService;
import msa.orderserver.vo.order.RequestOrder;
import msa.orderserver.vo.order.RequestUpdateOrder;
import msa.orderserver.vo.order.ResponseOrder;
import msa.orderserver.vo.order.ResponseUpdateOrder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {
    private final Environment env;
    private final OrderService orderService;
//    private final KafkaProducer kafkaProducer;
//    private final OrderProducer orderProducer;

    @GetMapping("/health_check")
    public String status(){
        return String.format("order service port %s",env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/order")
    public ResponseEntity<String> createOrder(@PathVariable("userId") String userId,
                                                     @RequestBody @Valid RequestOrder order){

//        OrderDto orderDto = order.toDto(userId);
//        orderDto.setOrderId(UUID.randomUUID().toString());
//        orderDto.setTotalPrice(orderDto.getQty()*orderDto.getUnitPrice());

        // kafka
        // 이건 catalog 서버로의 전달 토픽

//        kafkaProducer.send("example-order-topic", OrderToCatalogDto.from(order));
        // 이건 order db로의 전달 토픽
        // orderProducer.send("orderrrr",orderDto);

        orderService.createOrder(order,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body("good");
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId) {
        List<ResponseOrder> orderList = orderService.getOrdersByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    // 주문 취소
    @PutMapping("/{orderId}/cancel/order")
    public ResponseEntity<String> cancelOrder(@PathVariable("orderId") String orderId){
        ResponseUpdateOrder responseUpdateOrder = orderService.cancelOrder(orderId);
        if(responseUpdateOrder.getCheck()) return ResponseEntity.status(HttpStatus.OK).body("취소하였습니다.");
        else return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("취소 실패하였습니다.");
    }

    @PutMapping("{orderId}/order")
    public ResponseEntity<String> updateOrder(@PathVariable("orderId") String orderId,
                                              @RequestBody @Valid RequestUpdateOrder requestUpdateOrder){
        orderService.updateOrder(orderId,requestUpdateOrder);
        return ResponseEntity.status(HttpStatus.OK).body("변경 완료");
    }

}
