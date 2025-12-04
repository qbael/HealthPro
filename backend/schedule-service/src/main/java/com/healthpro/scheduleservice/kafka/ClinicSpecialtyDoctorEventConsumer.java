package com.healthpro.scheduleservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import com.healthpro.clinic.event.ClinicSpecialtyDoctorEvent;
import com.healthpro.scheduleservice.entity.ClinicSpecialtyDoctor;
import com.healthpro.scheduleservice.repository.ClinicSpecialtyDoctorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ClinicSpecialtyDoctorEventConsumer {

    private final ClinicSpecialtyDoctorRepository clinicSpecialtyDoctorRepository;

//    @KafkaListener(topics = "clinic-specialty-doctor", groupId = "schedule-service")
    public void consumeClinicSpecialtyDoctorEvent(
            @Payload byte[] data,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset,
            Acknowledgment acknowledgment
    ) {
        log.info("Received message from topic: {}, partition: {}, offset: {}", topic, partition, offset);
        log.info("Received ClinicSpecialtyDoctor event message");
        try {
            ClinicSpecialtyDoctorEvent event = ClinicSpecialtyDoctorEvent.parseFrom(data);

            log.info("Deserialized event - ID: {}, ClinicSpecialtyId: {}, DoctorId: {}, EventType: {}",
                    event.getId(),
                    event.getClinicSpecialtyId(),
                    event.getDoctorId(),
                    event.getEventType());

            switch (event.getEventType()) {
                case "CREATED" -> handleCreatedEvent(event);
                case "UPDATED" -> handleUpdatedEvent(event);
                case "DELETED" -> handleDeletedEvent(event);
                default -> log.warn("Unknown event type: {}", event.getEventType());
            }

            acknowledgment.acknowledge();
            log.info("Successfully processed and committed offset: {}", offset);

        } catch (InvalidProtocolBufferException e) {
            log.error("Failed to deserialize protobuf message at offset {}: {}", offset, e.getMessage());
        } catch (Exception e) {
            log.error("Error processing event at offset {}: {}", offset, e.getMessage(), e);
        }
    }

    private void handleCreatedEvent(ClinicSpecialtyDoctorEvent event) {
        if (clinicSpecialtyDoctorRepository.existsById(UUID.fromString(event.getId()))) {
            log.warn("ClinicSpecialtyDoctor already exists with id: {}", event.getId());
            return;
        }

        ClinicSpecialtyDoctor clinicSpecialtyDoctor = ClinicSpecialtyDoctor.builder()
                .id(UUID.fromString(event.getId()))
                .clinicSpecialtyId(UUID.fromString(event.getClinicSpecialtyId()))
                .doctorId(UUID.fromString(event.getDoctorId()))
                .assignmentCount(0)
                .build();

        clinicSpecialtyDoctorRepository.save(clinicSpecialtyDoctor);
        log.info("Created ClinicSpecialtyDoctor: {}", clinicSpecialtyDoctor);
    }

    private void handleUpdatedEvent(ClinicSpecialtyDoctorEvent event) {
        clinicSpecialtyDoctorRepository.findById(UUID.fromString(event.getId()))
                .ifPresentOrElse(
                        existing -> {
                            existing.setAssignmentCount(0);
                            clinicSpecialtyDoctorRepository.save(existing);
                            log.info("Updated ClinicSpecialtyDoctor: {}", existing);
                        },
                        () -> {
                            log.warn("ClinicSpecialtyDoctor not found with id: {}", event.getId());
                            handleCreatedEvent(event);
                        }
                );
    }

    private void handleDeletedEvent(ClinicSpecialtyDoctorEvent event) {
        clinicSpecialtyDoctorRepository.findById(UUID.fromString(event.getId()))
                .ifPresentOrElse(
                        existing -> {
                            clinicSpecialtyDoctorRepository.delete(existing);
                            log.info("Deleted ClinicSpecialtyDoctor with id: {}", event.getId());
                        },
                        () -> log.warn("ClinicSpecialtyDoctor not found with id: {}", event.getId())
                );
    }
}