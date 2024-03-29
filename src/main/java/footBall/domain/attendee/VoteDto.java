package footBall.domain.attendee;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoteDto {
    private int voteId;
    private LocalDateTime voteDate;
}
