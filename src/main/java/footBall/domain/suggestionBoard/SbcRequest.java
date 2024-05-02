package footBall.domain.suggestionBoard;

import lombok.Data;

@Data
public class SbcRequest {

    private int sbcId; // 답변 ID
    private int suggestionBoardId; // 해당 답변의 게시글 ID
    private String sbcAuthor; // 답변 작성자
    private String sbcText; // 답변 내용

}
