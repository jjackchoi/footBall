package footBall.freeBoardComment;

import lombok.Data;

@Data
public class FbcRequest {

    private int fbcId; // 댓글 ID
    private int freeBoardId; // 해당 댓글의 게시글 ID
    private String fbcAuthor; // 댓글 작성자
    private String fbcText; // 댓글 내용

}
