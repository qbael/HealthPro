package com.healthpro.clinicservice.kafka;

import com.healthpro.clinicservice.dto.ClinicSpecialtyDoctorEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@AllArgsConstructor
public class ClinicSpecialtyDoctorEventProducer {

    private static final String TOPIC = "clinic-specialty-doctor-events";

    private final KafkaTemplate<String, ClinicSpecialtyDoctorEvent> kafkaTemplate;

    public void sendClinicSpecialtyDoctorEvent(ClinicSpecialtyDoctorEvent event) {
        try {
            CompletableFuture<SendResult<String, ClinicSpecialtyDoctorEvent>> future =
                    kafkaTemplate.send(TOPIC, event.getId().toString(), event);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Sent message=[{}] with offset=[{}]",
                            event, result.getRecordMetadata().offset());
                } else {
                    log.error("Unable to send message=[{}] due to : {}",
                            event, ex.getMessage());
                }
            });
        } catch (Exception e) {
            log.error("Error sending Kafka message: {}", e.getMessage());
        }
    }
}