// 📄 이 클래스는 실제 HTTP 요청을 받아 사용자 관련 기능을 수행하는 Controller입니다.
// - REST API의 엔드포인트를 정의하고,
// - Service를 호출해서 비즈니스 로직을 수행하며,
// - 클라이언트에게 JSON 형태로 응답을 반환합니다.

package com.jinu.bitool.controller;

import com.jinu.bitool.entity.User;
import com.jinu.bitool.service.UserService;
import com.jinu.bitool.dto.UserRequestDTO;
import com.jinu.bitool.dto.UserResponseDTO;
import com.jinu.bitool.response.ApiResponse;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // ✅ REST API 전용 컨트롤러 (JSON으로 응답)
@RequiredArgsConstructor
//@Autowired // ✅ 생성자 주입: Spring이 UserService를 자동으로 넣어줌
////    public UserController(UserService userService) {
////        this.userService = userService;
////    } 자동으로 생성
@RequestMapping("/api/users") // ✅ 이 컨트롤러의 기본 경로는 /api/users
public class UserController {

    private final UserService userService;

//    @Autowired // ✅ 생성자 주입: Spring이 UserService를 자동으로 넣어줌
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    // ✅ 전체 사용자 조회 API (GET /api/users)
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers(); // 서비스에 위임
//    }
//    @GetMapping
//    public List<UserResponseDTO> getAllUsers() {
//        return userService.getAllUsers();
//    }
    // ✅ [GET] 전체 사용자 조회 API
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok()
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(new ApiResponse<>("SUCCESS", "전체 사용자 조회 성공", users));

    }

    // ✅ 사용자 단건 조회 API (GET /api/users/{id})
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        return userService.getUserById(id)
//                .map(ResponseEntity::ok)              // 찾았으면 200 OK
//                .orElseGet(() -> ResponseEntity.notFound().build()); // 없으면 404
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
//        return userService.getUserById(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
//        // ✅ 예외가 발생하면 GlobalExceptionHandler가 처리
//        UserResponseDTO responseDTO = userService.getUserById(id);
//        return ResponseEntity.ok(responseDTO); // 성공 시 200 OK + 데이터 반환
//    }
    /// ///////// 에러 코드////////////////////////
//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(@PathVariable Long id) {
//        UserResponseDTO user = userService.getUserById(id);
////        return ResponseEntity.ok(
////                new ApiResponse<>("SUCCESS", "사용자 조회 성공", user)
////        );
//        return ResponseEntity
//                .ok()
//                .header("Content-Type", "application/json; charset=UTF-8") // ✅ 이 한 줄
//                .body(new ApiResponse<>("SUCCESS", "사용자 조회 성공", user));
//    }
/// /////////////////////////////////////////////
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("해당 사용자가 존재하지 않습니다."));

        return ResponseEntity.ok()
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(new ApiResponse<>("SUCCESS", "사용자 조회 성공", user));
    }
    // ✅ 사용자 등록 API (POST /api/users)
//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User saved = userService.saveUser(user); // 저장하고 결과 반환
//        return ResponseEntity.ok(saved);         // 상태 200 OK + 저장된 데이터 반환
//    }
// 📄 사용자 등록 API
// - 이전에는 Entity(User)를 직접 받았지만,
// - 이제는 요청 전용 구조인 UserRequestDTO를 통해 데이터를 받습니다.
// - 저장이 완료되면 응답 전용 구조인 UserResponseDTO를 반환합니다.

//    @PostMapping
//    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO requestDTO) {
//        // ✅ UserService에 DTO를 넘기고, 응답 DTO를 받아서 클라이언트에 반환
//        UserResponseDTO responseDTO = userService.saveUser(requestDTO);
//        return ResponseEntity.ok(responseDTO); // 상태 코드 200 + JSON 응답
//    }
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(@RequestBody @Valid UserRequestDTO dto) {
        UserResponseDTO response = userService.saveUser(dto);
//        return ResponseEntity.ok(
//                new ApiResponse<>("SUCCESS", "사용자 등록 성공", response)
//        );
        return ResponseEntity
                .ok()
                .header("Content-Type", "application/json; charset=UTF-8") // ✅ 이 한 줄
                .body(new ApiResponse<>("SUCCESS", "사용자 등록 성공", response));
    }


    // ✅ 사용자 삭제 API (DELETE /api/users/{id})
    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build(); // 204 No Content
//    }
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity
                .ok()
                .header("Content-Type", "application/json; charset=UTF-8") // ✅ 이 한 줄
                .body(new ApiResponse<>("SUCCESS", "삭제 성공", null));

    }
}
