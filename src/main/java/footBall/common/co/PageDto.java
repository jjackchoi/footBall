package footBall.common.co;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PageDto {
    private int startPage, endPage;
    private boolean prev, next;
    private int total;
    private Criteria cri;

    public PageDto(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;

        // total 값이 0인 경우 페이징을 생성하지 않음
        if (total == 0) {
            this.startPage = 1;
            this.endPage = 1;
            this.prev = false;
            this.next = false;
            return;
        }

        int realEnd = (int) Math.ceil((double) total / cri.getAmount());
        int pageNum = cri.getPageNum();

        // 페이지 번호를 1부터 시작하도록 수정
        pageNum = Math.max(1, pageNum);

        // 시작 페이지와 끝 페이지 계산
        int tempEnd = (int) (Math.ceil(pageNum / 10.0)) * 10;
        this.startPage = Math.max(1, tempEnd - 9);
        this.endPage = Math.min(tempEnd, realEnd);

        // 이전 페이지와 다음 페이지 설정
        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }

}