// 📄 모든 API 응답을 감싸는 공통 응답 클래스입니다.
// - 성공/실패 여부, 메시지, 실제 데이터(payload)를 구조화해 반환합니다.

package com.jinu.bitool.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private String status;  // ✅ SUCCESS 또는 ERROR
    private String message; // ✅ 응답 메시지
    private T data;         // ✅ 실제 응답 데이터 (DTO 등)
}
