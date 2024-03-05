package footBall.attendee;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendeeDto {
    private int voteId;
    private LocalDateTime voteDate;
}
