package com.healthpro.clinicservice.kafka;

import com.healthpro.clinicservice.entity.ClinicSpecialtyDoctor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.healthpro.clinic.event.ClinicSpecialtyDoctorEvent;

@Slf4j
@Service
@AllArgsConstructor
public class ClinicSpecialtyDoctorEventProducer {

//    private final KafkaTemplate<String, byte[]> kafkaTemplate;
//
//    public void sendClinicSpecialtyDoctorEvent(ClinicSpecialtyDoctor clinicSpecialtyDoctor) {
//        ClinicSpecialtyDoctorEvent event = ClinicSpecialtyDoctorEvent.newBuilder()
//                .setId(clinicSpecialtyDoctor.getId().toString())
//                .setClinicSpecialtyId(clinicSpecialtyDoctor.getClinicSpecialty().getId().toString())
//                .setDoctorId(clinicSpecialtyDoctor.getDoctor().getId().toString())
//                .setEventType("CREATED")
//                .build();
//        try {
//            kafkaTemplate.send("clinic-specialty-doctor", event.toByteArray());
//            log.info("Sent ClinicSpecialtyDoctor event: {}", event);
//        } catch (Exception e) {
//            log.error("Error sending Kafka message: {}", e.getMessage());
//        }
//    }
}