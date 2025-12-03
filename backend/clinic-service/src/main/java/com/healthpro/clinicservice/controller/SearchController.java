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

    @GetMapping
    public ResponseEntity<ApiResponseDTO<SearchResponse>> search(
            @RequestParam(required = false, defaultValue = "") String q,
            @RequestParam(required = false, defaultValue = "all") String type,
            @RequestParam(required = false) String specialty,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit
    ) {
        SearchResponse result = searchService.globalSearch(q, type, specialty, page, limit);
        return ResponseEntity.ok(ApiResponseDTO.success(result, "Tìm kiếm thành công"));
    }
}