package com.jinu.bitool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// ✅ 1. 방사선 측정 원시 데이터 테이블과 매핑되는 Entity 클래스입니다.
// - DB 테이블: activcheckinfo
// - 사용 목적: datetime(측정시각), calculatedact(방사선량 계산값) 시각화를 위해 사용
// - @Entity 어노테이션은 JPA가 이 클래스를 DB 테이블과 연결되도록 해줍니다.

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "visual_activity") // 실제 테이블명 명시 !!!!
public class ActiveCheckInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number; // ✅ 기본 키 (Primary Key)

    private String userid; // ✅ 측정 기록 주체 ID (분석 대상이 될 수 있음)

    private LocalDateTime datetime; // ✅ 측정 시간 (X축 기준)

    private Double calculatedact; // ✅ 계산된 방사선량 수치 (Y축 기준)

    private Double guide_activity; // python으로 계산한 액티비티

    private Double upper_limit;

    private Double lower_limit;

    private int duration;
}