package msa.orderserver.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.orderserver.dto.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    List<Field> fields = Arrays.asList(new Field("string",true,"order_id"),
            new Field("string",true,"user_id"),
            new Field("string",true,"product_id"),
            new Field("int32",true,"qty"),
            new Field("int32",true,"total_price"),
            new Field("int32",true,"unit_price"));
    Schema schema = Schema.builder()
            .type("struct")
            .fields(fields)
            .optional(false)
            .name("orderrrr")
            .build();

    public void send(String topic, OrderDto orderDto){
        Payload payload = Payload.builder()
                .order_id(orderDto.getOrderId())
                .user_id(orderDto.getUserId())
                .product_id(orderDto.getProductId())
                .qty(orderDto.getQty())
                .unit_price(orderDto.getUnitPrice())
                .total_price(orderDto.getTotalPrice())
                .build();

        KafkaOrderDto kafkaOrderDto = new KafkaOrderDto(schema,payload);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try{
            jsonInString = mapper.writeValueAsString(kafkaOrderDto);
        }catch (JsonProcessingException ex){
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("order producer good kafka" + kafkaOrderDto);

    }
}
