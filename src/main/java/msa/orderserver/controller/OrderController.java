package msa.orderserver.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import msa.orderserver.domain.OrderEntity;
import msa.orderserver.dto.OrderDto;
import msa.orderserver.messagequeue.KafkaProducer;
import msa.orderserver.messagequeue.OrderProducer;
import msa.orderserver.service.OrderService;
import msa.orderserver.vo.RequestOrder;
import msa.orderserver.vo.ResponseOrder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {
    private final Environment env;
    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;
    private final OrderProducer orderProducer;

    @GetMapping("/health_check")
    public String status(){
        return String.format("order service port %s",env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
                                                     @RequestBody RequestOrder order){

//        OrderDto orderDto = orderService.createOrder(order.toDto(userId));

        OrderDto orderDto = order.toDto(userId);
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getQty()*orderDto.getUnitPrice());

        // kafka
        kafkaProducer.send("example-order-topic",orderDto);
        orderProducer.send("orderrrr",orderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseOrder.from(orderDto));
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId) {
        Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);
        List<ResponseOrder> result = new ArrayList<>();

        for (OrderEntity orderEntity : orderList) {
            result.add(ResponseOrder.fromEntity(orderEntity));
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
