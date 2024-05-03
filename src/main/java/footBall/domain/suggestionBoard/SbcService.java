package footBall.domain.suggestionBoard;

public interface SbcService {

    // 답변 등록 및 해당 게시글 답변 완료 상태로 변경
    void createSbc(SbcRequest params);

    // 해당 게시글 답변 조회
    SbcResponse getComment(int id);
}
