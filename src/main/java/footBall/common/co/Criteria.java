package footBall.common.co;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
    private int pageNum;
    private int amount;
    private int startOffset;
    private String keyword;

    public Criteria() {
        this(1,10);
    }


    public Criteria(int pageNum, int amount) {
        super();
        this.amount = amount;
        this.pageNum = pageNum;
        this.startOffset = (pageNum -1) * 10;
    }

    public Criteria(int pageNum, int amount ,String keyword) {
        super();
        this.amount = amount;
        this.pageNum = pageNum;
        this.keyword = keyword;
        this.startOffset = (pageNum -1) * 10;
    }
}
