// 📄 이 클래스는 클라이언트로부터 사용자 정보를 입력받을 때 사용하는 요청 DTO입니다.
// - 엔티티(User)를 직접 API에 사용하는 대신, 외부에서 받을 필드만 정의합니다.
// - 이렇게 하면 필요한 필드만 노출되고, 보안과 유지보수에 유리합니다.

package com.jinu.bitool.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // ✅ 모든 필드의 getter 자동 생성 (예: getUsername())
@Setter // ✅ 모든 필드의 setter 자동 생성 (예: setEmail())
@NoArgsConstructor // ✅ 기본 생성자 자동 생성 (JSON 파싱에 필요)
public class UserRequestDTO {

    // ✅ 사용자 이름: 프론트엔드에서 입력받을 필드
    @NotBlank(message = "이름은 비어 있을 수 없습니다.")
    private String username;

    // ✅ 사용자 이메일: 프론트에서 입력받을 필드
    @NotBlank(message = "이메일은 비어 있을 수 없습니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;
}
