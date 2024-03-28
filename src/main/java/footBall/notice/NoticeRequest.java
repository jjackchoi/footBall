package footBall.notice;

import lombok.Data;

@Data
public class NoticeRequest {
    private int noticeId;               // 공지사항 ID
    private int fbUserId;               // 유저 ID
    private String noticeAuthor;        // 공지사항 작성자
    private String noticeTitle;         // 공지사항 제목
    private String noticeText;          // 공지사항 내용
    private String noticeImgPath;       // 공지사항 이미지 경로
}
