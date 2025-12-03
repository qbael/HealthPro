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

    public SearchResponse globalSearch(String q, String type, String specialty, int page, int limit) {
        String keyword = (q == null || q.trim().isEmpty()) ? "" : q.trim();
        String lowerKeyword = keyword.toLowerCase();
        int offset = page * limit;

        List<Doctor> doctors = new ArrayList<>();
        List<Clinic> clinics = new ArrayList<>();

        Set<String> matchedSpecialties = findMatchedSpecialties(lowerKeyword);
        String specialtyListFromDisease = String.join(",", matchedSpecialties);

        String finalSpecialtyList = "";
        if (specialty != null && !specialty.trim().isEmpty() && !"all".equalsIgnoreCase(specialty)) {
            finalSpecialtyList = specialty.trim();
        } else if (!specialtyListFromDisease.isEmpty()) {
            finalSpecialtyList = specialtyListFromDisease;
        }

        System.out.println("=== SEARCH DEBUG ===");
        System.out.println("Keyword: " + keyword);
        System.out.println("Type: " + type);
        System.out.println("Specialty param: " + specialty);
        System.out.println("Final specialty list: " + finalSpecialtyList);

        // 2. Xử lý theo type
        if ("all".equalsIgnoreCase(type)) {
            // CASE: type=all, không có q, không có specialty → Hiện tất cả clinics
            if (keyword.isEmpty() && finalSpecialtyList.isEmpty()) {
                clinics = clinicRepository.findAllClinics(limit, offset);
                return new SearchResponse(doctors, clinics);
            }
            // CASE: type=all, có q hoặc có specialty → Tìm cả doctor + clinic
            doctors = doctorRepository.searchDoctorsByKeywordAndSpecialty(
                    keyword, finalSpecialtyList, limit, offset
            );
            clinics = clinicRepository.searchClinicsByKeywordAndSpecialty(
                    keyword, finalSpecialtyList, limit, offset
            );
        }
        else if ("doctor".equalsIgnoreCase(type)) {
            // CASE: type=doctor → Chỉ tìm doctor
            doctors = doctorRepository.searchDoctorsByKeywordAndSpecialty(
                    keyword, finalSpecialtyList, limit, offset
            );
        }
        else if ("clinic".equalsIgnoreCase(type)) {
            // CASE: type=clinic → Chỉ tìm clinic
            clinics = clinicRepository.searchClinicsByKeywordAndSpecialty(
                    keyword, finalSpecialtyList, limit, offset
            );
        }

        return new SearchResponse(doctors, clinics);
    }

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