package footBall.notice;

import java.util.List;

public interface NoticeService {

    // 공지사항 전체 조회
    List<NoticeResponse> getNotices();

    // 공지사항 생성
    int createNotice(NoticeRequest params);
}
