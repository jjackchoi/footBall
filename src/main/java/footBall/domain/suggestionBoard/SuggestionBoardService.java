package footBall.domain.suggestionBoard;

import java.util.List;

public interface SuggestionBoardService {

    // 전체 조회
    List<SuggestionBoardResponse> findAll();

    // 비밀글제외 조회
    List<SuggestionBoardResponse> findExcludeSecret();

    // 게시글 생성
    int create(SuggestionBoardRequest dto);

    // 게시글 상세 조회
    SuggestionBoardResponse findById(int id);

    // 내가쓴글조회
    List<SuggestionBoardResponse> findByUserId(int id);
}
