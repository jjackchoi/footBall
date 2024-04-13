package footBall.domain.notice;

import footBall.common.co.Criteria;

import java.util.List;

public interface NoticeService {

    // 공지사항 전체 조회
    List<NoticeResponse> getNotices(Criteria cri);

    // 공지사항 생성
    int createNotice(NoticeRequest params);

    int allCount();

    int searchAllCount(String keyword);

    List<NoticeResponse> getSearchNotices(Criteria cri);
}
