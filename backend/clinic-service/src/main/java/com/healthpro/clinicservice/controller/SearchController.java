package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ApiResponseDTO;
import com.healthpro.clinicservice.dto.SearchResponse;
import com.healthpro.clinicservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    /**
     * API tìm kiếm global (doctor + clinic)
     *
     * @param q - Từ khóa tìm kiếm (tên bác sĩ, phòng khám, hoặc bệnh)
     * @param type - Loại tìm kiếm: "all" (mặc định), "doctor", "clinic"
     * @param specialtyId - UUID chuyên khoa (optional) để lọc thêm
     * @param page - Trang hiện tại (default: 0)
     * @param limit - Số lượng kết quả mỗi trang (default: 20)
     *
     * Examples:
     * - /api/v2/search?type=all → Hiện tất cả clinics
     * - /api/v2/search?q=Viện&type=all → Tìm "Viện" (cả doctor + clinic)
     * - /api/v2/search?q=đau đầu&type=doctor → Tìm bác sĩ chuyên khoa Thần kinh
     * - /api/v2/search?q=&type=clinic&specialtyId=xxx → Lọc clinic theo chuyên khoa
     */
    @GetMapping
    public ResponseEntity<ApiResponseDTO<SearchResponse>> search(
            @RequestParam(required = false, defaultValue = "") String q,
            @RequestParam(required = false, defaultValue = "all") String type,
            @RequestParam(required = false) String specialtyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit
    ) {
        SearchResponse result = searchService.globalSearch(q, type, specialtyId, page, limit);
        return ResponseEntity.ok(ApiResponseDTO.success(result, "Tìm kiếm thành công"));
    }
}