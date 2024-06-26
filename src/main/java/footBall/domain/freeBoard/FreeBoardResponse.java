package footBall.domain.freeBoard;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FreeBoardResponse {

    private int freeBoardId;                // 자유게시판 ID
    private int fbUserId;                   // 유저 ID
    private String freeBoardAuthor;         // 자유게시판 작성자
    private String freeBoardTitle;          // 자유게시판 제목
    private String freeBoardText;           // 자유게시판 내용
    private LocalDateTime freeBoardRegDate; // 자유게시판 작성시간
    private LocalDateTime freeBoardModDate; // 자유게시판 수정시간
    private String freeBoardImgPath;        // 자유게시판 이미지
    private boolean freeBoardDelYn;         // 자유게시판 삭제여부
    private int fbcCommentsCount;          // 자유게시판 댓글 갯수

}
