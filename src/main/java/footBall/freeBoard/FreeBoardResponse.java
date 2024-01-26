package footBall.freeBoard;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FreeBoardResponse {

    private int freeBoardId;
    private int fbUserId;
    private String freeBoardAuthor;
    private String freeBoardTitle;
    private String freeBoardText;
    private LocalDateTime freeBoardRegDate;
    private LocalDateTime freeBoardModDate;
    private String freeBoardImgPath;
    private boolean freeBoardDelYn;

}
