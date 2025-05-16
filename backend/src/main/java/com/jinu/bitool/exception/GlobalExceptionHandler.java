// 📄 전역 예외 처리 클래스입니다.
// - 모든 컨트롤러에서 발생한 예외를 한 곳에서 포맷팅해서 처리합니다.

package com.jinu.bitool.exception;

import com.jinu.bitool.response.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // ✅ 전역 예외 처리기 등록
public class GlobalExceptionHandler {

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException e) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("status", 404);
//        body.put("error", "Not Found");
//        body.put("message", e.getMessage()); // ✅ "사용자 ID 999를 찾을 수 없습니다"
//        body.put("path", "/api/users/{id}");
//
////        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .header("Content-Type", "application/json; charset=UTF-8") // ✅ 인코딩 명시
//                .body(body);
//    }
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ApiResponse<Object>> handleUserNotFound(UserNotFoundException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .header("Content-Type", "application/json; charset=UTF-8") // ✅ 인코딩 명시
//                .body(new ApiResponse<Object>("ERROR", e.getMessage(), null));
////    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//
//        ex.getBindingResult().getFieldErrors().forEach(error -> {
//            errors.put(error.getField(), error.getDefaultMessage());
//        });
//
//        return ResponseEntity
//                .badRequest()
//                .header("Content-Type", "application/json; charset=UTF-8") // ✅ 인코딩 명시
//                .body(new ApiResponse<Map<String, String>>("ERROR", "잘못된 요청입니다.", errors));
//    }

// ✅ 사용자 찾기 실패
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserNotFound(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(new ApiResponse<>("ERROR", e.getMessage(), null));
    }

    // ✅ 유효성 검증 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest()
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(new ApiResponse<>("ERROR", "유효성 검사 실패", errors));
    }

    // ✅ 기타 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralError(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(new ApiResponse<>("ERROR", "서버 에러: " + e.getMessage(), null));
    }
}
