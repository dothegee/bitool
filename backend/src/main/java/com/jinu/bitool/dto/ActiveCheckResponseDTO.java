package com.jinu.bitool.dto;

import java.time.LocalDateTime;

// ✅ 2. Controller → Front로 넘겨줄 JSON 응답 객체
// - record는 불변 구조로 DTO를 간결하게 정의할 수 있음
// - 프론트에서는 이 필드들을 바로 chart.js의 데이터로 사용할 수 있습니다.

public record ActiveCheckResponseDTO(
        LocalDateTime datetime,
        Double calculatedact,
        Double guide_activity,// python으로 계산한 액티비티
        Double upper_limit,
        Double lower_limit,
        int duration
) {}

