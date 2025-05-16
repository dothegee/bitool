// 📄 이 클래스는 PostgreSQL의 'users' 테이블과 연결되는 JPA 엔티티입니다.
// - '@Entity' 어노테이션으로 DB 테이블과 매핑되며,
// - '@Table(name = "users")' 덕분에 테이블 이름을 명시적으로 'users'로 설정해 예약어 충돌을 방지합니다.
// - 이 클래스는 사용자 정보를 표현하는 데이터 구조입니다.

package com.jinu.bitool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table; // ✅ 추가 필요!

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // ✅ 이 클래스는 DB 테이블로 매핑됨
@Table(name = "users") // ✅ 테이블 이름을 'users'로 명시 (PostgreSQL 예약어 'user' 피하기)
@Getter // ✅ 모든 필드에 대해 Getter 메서드 자동 생성
@Setter // ✅ 모든 필드에 대해 Setter 메서드 자동 생성
@NoArgsConstructor // ✅ 기본 생성자 자동 생성 (JPA 필수)
public class User {

    @Id // ✅ 이 필드는 테이블의 기본 키로 사용됨
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ✅ 자동 증가 설정 (PostgreSQL: SERIAL, MySQL: AUTO_INCREMENT)
    private Long id;

    private String username; // ✅ 사용자 이름 필드 (컬럼명: username)
    private String email;    // ✅ 사용자 이메일 필드 (컬럼명: email)
}
