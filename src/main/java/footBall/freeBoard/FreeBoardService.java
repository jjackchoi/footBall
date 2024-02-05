package footBall.freeBoard;

import java.util.List;

public interface FreeBoardService {
    List<FreeBoardResponse> findAll();

    int boardCreate(FreeBoardRequest dto);

    FreeBoardResponse findOne(int id);
}
