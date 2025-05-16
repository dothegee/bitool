// 📄 이 클래스는 사용자 정보를 클라이언트에 응답할 때 사용하는 DTO입니다.
// - Entity 대신 응답 전용 구조를 만들어 외부 노출을 제어하고,
// - 필요한 정보만 추려서 깔끔하게 내려주는 것이 목적입니다.

package com.jinu.bitool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // ✅ 모든 필드의 getter 자동 생성
@Setter // ✅ 모든 필드의 setter 자동 생성
@NoArgsConstructor // ✅ 기본 생성자 자동 생성
public class UserResponseDTO {

    // ✅ 사용자 고유 ID (Entity에서 가져온 값)
    private Long id;

    // ✅ 사용자 이름
    private String username;

    // ✅ 사용자 이메일
    private String email;

    // ✅ 생성자 오버로딩: Entity → DTO로 변환할 때 사용
    public UserResponseDTO(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
