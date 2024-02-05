package footBall.freeBoardComment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FbcResponse {

    private int fbcId; // 댓글 ID
    private int freeBoardId; // 해당 댓글의 게시글 ID
    private String fbcAuthor; // 댓글 작성자
    private String fbcText; // 댓글 내용
    private LocalDateTime fbcRegDate; // 댓글 작성일
    private LocalDateTime fbcModDate; // 댓글 수정일
    private Boolean fbcDelYn; // 댓글 삭제여부

}
