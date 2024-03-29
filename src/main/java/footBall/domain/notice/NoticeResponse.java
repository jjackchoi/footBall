package footBall.domain.notice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeResponse {
    private int noticeId;               // 공지사항 ID
    private int fbUserId;               // 유저 ID
    private String noticeAuthor;        // 공지사항 작성자
    private String noticeTitle;         // 공지사항 제목
    private String noticeText;          // 공지사항 내용
    private LocalDateTime noticeRegDate;// 공지사항 작성시간
    private LocalDateTime noticeModDate;// 공지사항 수정시간
    private String noticeImgPath;       // 공지사항 이미지 경로
    private String noticeDelYn;         // 공지사항 삭제 여부
}
