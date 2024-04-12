package footBall.domain.attendee;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@Data
public class VoteDto {
    private int voteId;
    private LocalDateTime voteDate;

    public LocalDateTime getSunday(){
        // 현재 날짜 가져오기
        LocalDateTime today = LocalDateTime.now();

        // 날짜의 요일 가져오기
        DayOfWeek todayOfWeek = today.getDayOfWeek();

        // 요일 숫자 출력(월요일:1, 화요일:2,..., 일요일:7)
        int todayOfWeekValue = todayOfWeek.getValue();

        // 투표 대상 일요일 날짜 가져오기
        LocalDateTime sunday = null;
        if (todayOfWeekValue == 7){
            sunday = today;
        } else {
            sunday = LocalDateTime.now();
            sunday = sunday.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)); // 다음 일요일 가져오기
        }
        sunday = sunday.withHour(0).withMinute(0).withSecond(0).withNano(0);
        return sunday;
    }
}
