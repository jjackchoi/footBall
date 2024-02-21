package footBall.suggestionBoard;

import lombok.Data;

@Data
public class SuggestionBoardRequest {

    private int suggestionBoardId; // 건의게시판 ID
    private int fbUserId; // 유저 ID
    private String suggestionBoardAuthor; // 건의게시판 작성자
    private String suggestionBoardTitle; // 건의게시판 제목
    private String suggestionBoardText; // 건의게시판 내용
    private String suggestionBoardImgPath; // 건의게시판 이미지
    private String suggestionBoardSecYn; // 건의게시판 비밀글여부

}
