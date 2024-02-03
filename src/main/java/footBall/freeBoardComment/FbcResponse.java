package footBall.freeBoardComment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FbcResponse {

    private int fbcId; // 댓글 ID
    private int free_board_id; // 해당 댓글의 게시글 ID
    private String fbc_author; // 댓글 작성자
    private String fbc_text; // 댓글 내용
    private LocalDateTime fbc_reg_date; // 댓글 작성일
    private LocalDateTime fbc_mod_date; // 댓글 수정일
    private Boolean fbc_del_yn; // 댓글 삭제여부

}
