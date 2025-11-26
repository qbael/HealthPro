package com.healthpro.scheduleservice.kafka;

import com.healthpro.scheduleservice.entity.ClinicSpecialtyDoctor;
import com.healthpro.scheduleservice.dto.ClinicSpecialtyDoctorEvent;
import com.healthpro.scheduleservice.repository.ClinicSpecialtyDoctorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ClinicSpecialtyDoctorEventConsumer {

    private final ClinicSpecialtyDoctorRepository clinicSpecialtyDoctorRepository;

    @KafkaListener(
            topics = "clinic-specialty-doctor-events",
            groupId = "schedule-service-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeClinicSpecialtyDoctorEvent(
            @Payload ClinicSpecialtyDoctorEvent event,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset
    ) {
        log.info("=================================================");
        log.info("Received message from Kafka:");
        log.info("Topic: {}, Partition: {}, Offset: {}", topic, partition, offset);
        log.info("Event: {}", event);
        log.info("=================================================");

        try {
            switch (event.getEventType()) {
                case CREATED:
                    handleCreatedEvent(event);
                    break;
                case UPDATED:
                    handleUpdatedEvent(event);
                    break;
                case DELETED:
                    handleDeletedEvent(event);
                    break;
                default:
                    log.warn("Unknown event type: {}", event.getEventType());
            }
        } catch (Exception e) {
            log.error("Error processing ClinicSpecialtyDoctor event: {}", e.getMessage(), e);
        }
    }

    private void handleCreatedEvent(ClinicSpecialtyDoctorEvent event) {
        if (clinicSpecialtyDoctorRepository.existsById(event.getId())) {
            log.warn("ClinicSpecialtyDoctor already exists with id: {}", event.getId());
            return;
        }

        ClinicSpecialtyDoctor clinicSpecialtyDoctor = ClinicSpecialtyDoctor.builder()
                .id(event.getId())
                .clinicSpecialtyId(event.getClinicSpecialtyId())
                .doctorId(event.getDoctorId())
                .assignmentCount(event.getAssignmentCount() != null ? event.getAssignmentCount() : 0)
                .build();

        clinicSpecialtyDoctorRepository.save(clinicSpecialtyDoctor);
        log.info("Created ClinicSpecialtyDoctor: {}", clinicSpecialtyDoctor);
    }

    private void handleUpdatedEvent(ClinicSpecialtyDoctorEvent event) {
        clinicSpecialtyDoctorRepository.findById(event.getId())
                .ifPresentOrElse(
                        existing -> {
                            existing.setAssignmentCount(event.getAssignmentCount());
                            clinicSpecialtyDoctorRepository.save(existing);
                            log.info("Updated ClinicSpecialtyDoctor: {}", existing);
                        },
                        () -> {
                            log.warn("ClinicSpecialtyDoctor not found with id: {}", event.getId());
                            // Nếu không tìm thấy, có thể tạo mới
                            handleCreatedEvent(event);
                        }
                );
    }

    private void handleDeletedEvent(ClinicSpecialtyDoctorEvent event) {
        clinicSpecialtyDoctorRepository.findById(event.getId())
                .ifPresentOrElse(
                        existing -> {
                            clinicSpecialtyDoctorRepository.delete(existing);
                            log.info("Deleted ClinicSpecialtyDoctor with id: {}", event.getId());
                        },
                        () -> log.warn("ClinicSpecialtyDoctor not found with id: {}", event.getId())
                );
    }
}