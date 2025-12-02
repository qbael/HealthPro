package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.dto.SearchResponse;
import com.healthpro.clinicservice.entity.Clinic;
import com.healthpro.clinicservice.entity.Doctor;
import com.healthpro.clinicservice.repository.ClinicRepository;
import com.healthpro.clinicservice.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final DoctorRepository doctorRepository;
    private final ClinicRepository clinicRepository;

    // Hard-code mapping bệnh → chuyên khoa
    private static final Map<String, Set<String>> DISEASE_TO_SPECIALTY;
    static {
        var map = new HashMap<String, Set<String>>();
        map.put("đau đầu", Set.of("Thần kinh"));
        map.put("đau nửa đầu", Set.of("Thần kinh"));
        map.put("migraine", Set.of("Thần kinh"));
        map.put("chóng mặt", Set.of("Thần kinh"));
        map.put("động kinh", Set.of("Thần kinh"));

        map.put("mụn", Set.of("Da liễu"));
        map.put("mụn trứng cá", Set.of("Da liễu"));
        map.put("viêm da", Set.of("Da liễu"));
        map.put("ngứa", Set.of("Da liễu"));
        map.put("rụng tóc", Set.of("Da liễu"));

        map.put("thai", Set.of("Sản - Phụ khoa"));
        map.put("khám thai", Set.of("Sản - Phụ khoa"));
        map.put("phụ khoa", Set.of("Sản - Phụ khoa"));

        map.put("tiểu đường", Set.of("Nội tổng quát", "Tim mạch"));
        map.put("đái tháo đường", Set.of("Nội tổng quát", "Tim mạch"));
        map.put("huyết áp", Set.of("Tim mạch"));
        map.put("cao huyết áp", Set.of("Tim mạch"));

        map.put("đau bụng", Set.of("Tiêu hóa"));
        map.put("dạ dày", Set.of("Tiêu hóa"));
        map.put("trào ngược", Set.of("Tiêu hóa"));

        map.put("sốt", Set.of("Nhi khoa"));
        map.put("trẻ em", Set.of("Nhi khoa"));
        map.put("hen", Set.of("Hô hấp"));
        map.put("hen suyễn", Set.of("Hô hấp"));
        map.put("hen phế quản", Set.of("Hô hấp"));

        map.put("đau lưng", Set.of("Chỉnh hình"));
        map.put("thoát vị", Set.of("Chỉnh hình"));

        DISEASE_TO_SPECIALTY = Map.copyOf(map);
    }

    public SearchResponse globalSearch(String q, String type, String specialtyName, int page, int limit) {
        String keyword = q != null ? q.trim() : "";
        int offset = page * limit;

        // 1. Mapping bệnh → chuyên khoa (tên)
        Set<String> matchedSpecialties = findMatchedSpecialties(keyword.toLowerCase());
        String specialtyListFromDisease = String.join(",", matchedSpecialties);

        // 2. Ưu tiên: nếu người dùng chọn chuyên khoa từ dropdown → dùng tên đó
        String finalSpecialtyList = "";
        if (specialtyName != null && !specialtyName.trim().isEmpty() && !"all".equals(specialtyName)) {
            finalSpecialtyList = specialtyName.trim();
        } else if (!specialtyListFromDisease.isEmpty()) {
            finalSpecialtyList = specialtyListFromDisease;
        }

        // 3. Luôn để specialtyId = "" để bỏ qua điều kiện UUID
        String finalSpecialtyId = "";

        // 4. Case không có keyword → hiện cả bác sĩ + bệnh viện
        if (keyword.isEmpty()) {
            List<Doctor> doctors = List.of();
            List<Clinic> clinics = clinicRepository.findAllClinics(limit, offset);

            if ("all".equalsIgnoreCase(type) || "doctor".equalsIgnoreCase(type)) {
                doctors = doctorRepository.searchDoctorsByKeywordAndSpecialty("", "", finalSpecialtyId, limit, offset);
            }
            return new SearchResponse(doctors, clinics);
        }

        // 5. Có keyword → tìm bình thường
        List<Doctor> doctors = List.of();
        List<Clinic> clinics = List.of();

        if ("all".equalsIgnoreCase(type)) {
            doctors = doctorRepository.searchDoctorsByKeywordAndSpecialty(keyword, finalSpecialtyList, finalSpecialtyId, limit, offset);
            clinics = clinicRepository.searchClinicsByKeywordAndSpecialty(keyword, finalSpecialtyList, finalSpecialtyId, limit, offset);
        } else if ("doctor".equalsIgnoreCase(type)) {
            doctors = doctorRepository.searchDoctorsByKeywordAndSpecialty(keyword, finalSpecialtyList, finalSpecialtyId, limit, offset);
        } else if ("clinic".equalsIgnoreCase(type)) {
            clinics = clinicRepository.searchClinicsByKeywordAndSpecialty(keyword, finalSpecialtyList, finalSpecialtyId, limit, offset);
        }

        return new SearchResponse(doctors, clinics);
    }

    /**
     * Tìm chuyên khoa phù hợp từ keyword (mapping bệnh)
     */
    private Set<String> findMatchedSpecialties(String lowerKeyword) {
        Set<String> matched = new HashSet<>();
        String[] words = lowerKeyword.split("\\s+");

        for (String word : words) {
            for (var entry : DISEASE_TO_SPECIALTY.entrySet()) {
                String disease = entry.getKey();
                if (disease.contains(word) || word.contains(disease)) {
                    matched.addAll(entry.getValue());
                }
            }
        }

        return matched;
    }
}