package com.jinu.bitool.controller;

import com.jinu.bitool.dto.ActiveCheckResponseDTO;
import com.jinu.bitool.response.ApiResponse;
import com.jinu.bitool.service.ActiveCheckInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
// ✅ 5. HTTP 요청을 받아 서비스 계층에 전달하고 응답을 만들어주는 계층입니다.
// - URL 경로는 /api/radiation-activity
// - GET 요청 시 start, end 시간 범위를 받아 해당 구간 데이터를 JSON 형태로 반환합니다.

import lombok.extern.slf4j.Slf4j; // 로그 기록을 위해

@Slf4j // 로그 기록을 위해
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/radiation-activity")
public class ActiveCheckInfoController {

    private final ActiveCheckInfoService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ActiveCheckResponseDTO>>> getActiveData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        log.info("🟢 [Controller] 측정값 요청 시작"); // 로그 기록  (요청 수신, 응답 반환, 입력 파라미터 확인)
        // ✅ Service 호출: 시간 범위 데이터를 조회
        List<ActiveCheckResponseDTO> data = service.getDataBetween(start, end);

        // ✅ API 응답 형식을 통일하기 위해 ApiResponse로 감쌉니다.
        return ResponseEntity
                .ok()
                .header("Content-Type", "application/json; charset=UTF-8") // ✅ JSON UTF-8로 명시
                .body(new ApiResponse<>("SUCCESS", "활성도 데이터 조회 성공", data));
    }
}
