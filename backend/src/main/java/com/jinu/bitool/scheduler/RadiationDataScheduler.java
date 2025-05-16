package com.jinu.bitool.scheduler;

import com.jinu.bitool.service.ActiveCheckInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
//@RequiredArgsConstructor
public class RadiationDataScheduler {

//    private final RadiationDataService service;
//
//    // 매 정각 실행 (ex: 10:00:00, 11:00:00)
//    @Scheduled(cron = "0 0 * * * *")
//    public void preloadThisHourData() {
//        LocalDateTime now = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
//        LocalDateTime nextHour = now.plusHours(1);
//        service.getDataBetween(now, nextHour); // ✅ 호출 시 캐시에 저장됨
//    }

    private final ActiveCheckInfoService service;
    private final boolean enabled;

    // ✅ 생성자 주입 + 설정 값 가져오기
    public RadiationDataScheduler(ActiveCheckInfoService service,
                                  @Value("${scheduler.enabled:true}") boolean enabled) {
        this.service = service;
        this.enabled = enabled;
    }

    @Scheduled(cron = "0 0 * * * *") // 매 시간 정각
    public void preloadThisHourData() {
        if (!enabled) {
            System.out.println("⏹️ [스케줄러 OFF] 자동 캐싱 비활성화 상태입니다.");
            return;
        }

        LocalDateTime now = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        LocalDateTime next = now.plusHours(1);

        System.out.println("⏳ [스케줄러 실행] " + now + " ~ " + next + " 구간 데이터 미리 캐싱");
        service.getDataBetween(now, next);
    }
}