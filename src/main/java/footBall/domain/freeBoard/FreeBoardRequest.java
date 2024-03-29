package footBall.domain.freeBoard;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FreeBoardRequest {

    private int freeBoardId; //자유게시판 ID
    private int fbUserId; //유저 ID
    private String freeBoardAuthor; //자유게시판 작성자
    private String freeBoardTitle; //자유게시판 제목
    private String freeBoardText; //자유게시판 내용
    private String freeBoardImgPath; //자유게시판 이미지
}
