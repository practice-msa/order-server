package msa.orderserver.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.orderserver.dto.OrderToCatalogDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderToCatalogDto send(String topic, OrderToCatalogDto orderDto){
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try{
            jsonInString = mapper.writeValueAsString(orderDto);
        }catch (JsonProcessingException ex){
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("good kafka" + orderDto);

        return orderDto;
    }
}
