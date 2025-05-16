// 📄 이 인터페이스는 Spring Data JPA에서 제공하는 기본 Repository를 확장합니다.
// - 'JpaRepository'를 상속하면 SQL 없이도 자동으로 save, findAll, findById 등의 메서드를 사용할 수 있습니다.
// - 제네릭 타입으로 <User, Long>을 지정하면 이 리포지토리는 User 엔티티를 Long 타입의 ID로 관리하게 됩니다.

package com.jinu.bitool.repository;

import com.jinu.bitool.entity.User; // User 엔티티 import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // ✅ 이 인터페이스가 Spring이 관리하는 리포지토리 컴포넌트라는 표시
public interface UserRepository extends JpaRepository<User, Long> {
    // ✅ JpaRepository<User, Long> 설명:
    // - User: 이 리포지토리가 다룰 엔티티 클래스
    // - Long: 해당 엔티티의 ID 필드 타입

    // 🔽 필요하다면 아래처럼 커스텀 쿼리 메서드도 선언 가능 (자동 구현됨)
    // Optional<User> findByUsername(String username);
}
