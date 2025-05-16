package com.jinu.bitool.repository;

import com.jinu.bitool.entity.ActiveCheckInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

// ✅ 3. DB에서 조건에 맞는 데이터를 읽어오는 JPA 인터페이스입니다.
// - JpaRepository<엔티티, ID 타입> 형태로 사용
// - 메서드 이름만으로 조건 검색 쿼리를 자동 생성해줍니다.
// - datetime BETWEEN start AND end 조건으로 정렬 조회

public interface ActiveCheckInfoRepository extends JpaRepository<ActiveCheckInfo, Long> {

    List<ActiveCheckInfo> findByDatetimeBetweenOrderByDatetimeAsc(
            LocalDateTime start, LocalDateTime end
    );

}
