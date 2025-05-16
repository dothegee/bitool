// 📄 사용자를 찾을 수 없을 때 발생시키는 커스텀 예외입니다.
// - Service 계층에서 ID 조회 실패 시 던지게 됩니다.

package com.jinu.bitool.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("사용자 ID " + id + "을(를) 찾을 수 없습니다.");
    }
}
