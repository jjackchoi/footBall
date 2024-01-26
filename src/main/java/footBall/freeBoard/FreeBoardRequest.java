package footBall.freeBoard;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FreeBoardRequest {

    private int freeBoardId;
    private int fbUserId;
    private String freeBoardAuthor;
    private String freeBoardTitle;
    private String freeBoardText;
    private String freeBoardImgPath;

}
