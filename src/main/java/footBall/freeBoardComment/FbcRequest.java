package footBall.freeBoardComment;

import lombok.Data;

@Data
public class FbcRequest {

    private int fbcId; // 댓글 ID
    private int free_board_id; // 해당 댓글의 게시글 ID
    private String fbc_author; // 댓글 작성자
    private String fbc_text; // 댓글 내용

}
