// 📄 이 클래스는 사용자 관련 비즈니스 로직을 담당하는 'Service 계층'입니다.
// - 외부 요청을 직접 받지 않고, Controller와 Repository 사이에서 중간 역할을 합니다.
// - 앞으로 사용자 등록, 조회, 삭제, 수정 등의 로직이 여기에 들어가게 됩니다.

package com.jinu.bitool.service;

import com.jinu.bitool.entity.User; // DB와 매핑된 사용자 엔티티 클래스
import com.jinu.bitool.repository.UserRepository; // DB 접근용 리포지토리
import com.jinu.bitool.dto.UserRequestDTO;
import com.jinu.bitool.dto.UserResponseDTO;
import com.jinu.bitool.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // ✅ 이 어노테이션을 붙이면 Spring이 이 클래스를 Service Bean으로 등록해줌
@RequiredArgsConstructor
//@Autowired // ✅ 생성자 기반 의존성 주입: Spring이 자동으로 Repository 객체를 넣어줌
////    public UserService(UserRepository userRepository) {
////        this.userRepository = userRepository;
////    } 자동으로 만들어줌
public class UserService {

    // ✅ UserRepository는 DB와 직접 연결되는 레이어.
    // 우리는 직접 new로 만들지 않고, Spring이 자동으로 주입해주게 설정함.
    private final UserRepository userRepository;

//    @Autowired // ✅ 생성자 기반 의존성 주입: Spring이 자동으로 Repository 객체를 넣어줌
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    // ✅ 사용자 전체 조회
//    public List<User> getAllUsers() {
//        // Repository에서 findAll()을 호출하여 전체 사용자 목록을 조회
//        return userRepository.findAll();
//    }
    public List<UserResponseDTO> getAllUsers() {
        // ✅ 기존: Entity 리스트
        // return userRepository.findAll();

        // ✅ 변경: Entity → DTO로 변환
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }

    // ✅ ID로 사용자 1명 조회
//    public Optional<User> getUserById(Long id) {
//        // findById는 Optional로 감싸져 있어서 null-safe하게 처리 가능
//        return userRepository.findById(id);
//    }
//    public Optional<UserResponseDTO> getUserById(Long id) {
//        // ✅ 기존: Optional<User>
//        // return userRepository.findById(id);
//
//        // ✅ 변경: Optional<UserResponseDTO>
//        return userRepository.findById(id)
//                .map(user -> new UserResponseDTO(
//                        user.getId(),
//                        user.getUsername(),
//                        user.getEmail()
//                ));
//    }

// 📄 단일 사용자 조회 서비스 로직
// - 존재하지 않으면 예외를 던지고
// - 존재하면 Entity → ResponseDTO로 변환해서 반환

//    public UserResponseDTO getUserById(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id)); // ✅ 커스텀 예외 던지기
//
//        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail());
//    }
    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail()
                ));
    }

    // ✅ 사용자 저장
//    public User saveUser(User user) {
//        // Repository의 save()를 사용하면 자동으로 INSERT 또는 UPDATE 쿼리가 실행됨
//        return userRepository.save(user);
//    }

    // ✅ 사용자 삭제
//    public void deleteUser(Long id) {
//        // 존재 여부 확인 없이 바로 삭제
//        userRepository.deleteById(id);
//    }
    // 💡 UserService.java 내부에 아래 메서드 추가
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }
    // 📄 사용자 등록 API
// - 이전에는 Entity(User)를 직접 받았지만,
// - 이제는 요청 전용 구조인 UserRequestDTO를 통해 데이터를 받습니다.
// - 저장이 완료되면 응답 전용 구조인 UserResponseDTO를 반환합니다.

// 📄 사용자 저장 비즈니스 로직
// - 요청 DTO를 받아 Entity(User)로 변환한 뒤 저장하고,
// - 저장된 Entity를 응답 DTO로 다시 변환해서 반환합니다.

    public UserResponseDTO saveUser(UserRequestDTO dto) {
        // ✅ 요청 DTO → Entity로 변환
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());

        // ✅ 저장
        User saved = userRepository.save(user);

        // ✅ Entity → 응답 DTO로 변환
        return new UserResponseDTO(saved.getId(), saved.getUsername(), saved.getEmail());
    }

}
