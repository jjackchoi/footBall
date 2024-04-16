package footBall.domain.notice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class NoticeServiceImplTest {

    @Autowired
    private NoticeService noticeService;
    @Test
    void createNotice() {
        for (int i = 1; i <= 1000; i ++){
            NoticeRequest params = new NoticeRequest();
            params.setFbUserId(9);
            params.setNoticeAuthor("짹초이");
            params.setNoticeTitle(i +"번째 글입니다.");
            params.setNoticeText(i + "번째 내용입니다.");
            noticeService.createNotice(params);
        }
    }
}