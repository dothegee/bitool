package com.jinu.bitool.scheduler;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ✅ 캐시 초기화 스케줄러
 * - 특정 시간마다 radiationActivityCache를 비움
 * - 캐시 삭제 후 다음 요청 시 DB에서 새로 조회됨
 */

@Component
public class ActiveCheckCacheCleaner {

    // ✅ 1시간마다 전체 캐시 삭제
    // 0 * * * * * 으로 바꾸면 매 분마다 캐시 삭제
    // 0 0 * * * * // 매 정각 0분 실행
    @Scheduled(cron = "0 * * * * *") // 매 정각 0분 실행
    @CacheEvict(value = "radiationActivityCache", allEntries = true)
    public void clearCacheHourly() {
        System.out.println("🧹 [캐시 초기화] radiationActivityCache 전부 삭제됨 (매 정각)");
    }
}
