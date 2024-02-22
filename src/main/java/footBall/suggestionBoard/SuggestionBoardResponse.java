package footBall.suggestionBoard;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SuggestionBoardResponse {

    private int suggestionBoardId; // 건의게시판 ID
    private int fbUserId; // 유저 ID
    private String suggestionBoardAuthor; // 건의게시판 작성자
    private String suggestionBoardTitle; // 건의게시판 제목
    private String suggestionBoardText; // 건의게시판 내용
    private LocalDateTime suggestionBoardRegDate; // 건의게시판 작성시간
    private LocalDateTime suggestionBoardModDate; // 건의게시판 수정시간
    private String suggestionBoardImgPath; // 건의게시판 이미지
    private boolean suggestionBoardDelYn; // 건의게시판 삭제여부
    private String suggestionBoardSolYn; // 건의게시판 해결여부
    private String suggestionBoardSecYn; // 건의게시판 비밀글여부

}
