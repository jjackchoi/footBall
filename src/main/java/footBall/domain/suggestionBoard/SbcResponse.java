package footBall.domain.suggestionBoard;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SbcResponse {

    private int sbcId; // 답변 ID
    private int suggestionBoardId; // 해당 답변의 게시글 ID
    private String sbcAuthor; // 답변 작성자
    private String sbcText; // 답변 내용
    private LocalDateTime sbcRegDate; // 답변 작성일
    private LocalDateTime sbcModDate; // 답변 수정일
    private Boolean sbcDelYn; // 답변 삭제여부

}
