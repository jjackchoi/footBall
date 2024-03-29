package footBall.domain.user;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final SqlSession sqlSession;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional
    public int userRegister(UserRequest userRequest){
        userRequest.encodingPassword(passwordEncoder);
        return sqlSession.insert("UserMapper.register", userRequest);
    }

    // 유저 전체 조회
    @Override
    public List<UserResponse> getAllUser(){
        return sqlSession.selectList("UserMapper.getAllUser");
    }

    // 이메일로 유저 조회
    @Override
    public List<UserResponse> getUserByEmail(String email) {
        return sqlSession.selectList("UserMapper.getUserByEmail", email);
    }

    // 닉네임으로 유저 조회
    @Override
    public List<UserResponse> getUserByNickname(String nickname) {
        return sqlSession.selectList("UserMapper.getUserByNickname", nickname);
    }

    // 유저 멤버만 조회
    @Override
    public List<UserResponse> getAllMember() {
        return sqlSession.selectList("UserMapper.getAllMember");
    }

    // 로그인
    @Override
    @Transactional
    public int login(UserRequest dto) {
        // 1. 회원 정보 및 비밀번호 조회
        UserResponse userDto = sqlSession.selectOne("UserMapper.getUserByEmail", dto.getFbUserEmail());
        String encodedPassword = (userDto == null) ? "" : userDto.getFbUserPswd();

        // 2. 회원 정보 및 비밀번호 체크
        if (userDto == null || passwordEncoder.matches(dto.getFbUserPswd(), encodedPassword) == false){
            return 0;
        }

        return userDto.getFbUserId();
    }

    // 닉네임 조회
    @Override
    public String findNickname(int id) {
        return sqlSession.selectOne("UserMapper.findNickname",id);
    }

    // 정보 조회
    @Override
    public UserResponse findOne(Integer id) {
        return sqlSession.selectOne("UserMapper.findOne",id);
    }

    // 이름과 이메일로 존재여부 판별
    @Override
    public Integer checkByNameAndEmail(UserRequest params) {
        return sqlSession.selectOne("UserMapper.checkByNameAndEmail", params);
    }

    // 비밀번호 수정(params: 이메일, 이름, 비밀번호)
    @Override
    @Transactional
    public int modifyPassword(UserRequest params) {
        params.encodingPassword(passwordEncoder);
        return sqlSession.update("UserMapper.modifyPassword", params);
    }

    // 멤버 정보 기입 및 수정
    @Override
    public int insertMemInfo(UserRequest params) {
        return sqlSession.update("UserMapper.insertMemInfo", params);
    }

    // 프로필 사진 업데이트
    @Override
    @Transactional
    public void updateProfile(MultipartFile profileImg, String webPath, String filePath, UserResponse loginUser) throws IOException {
        // 프로필 이미지 변경 실패 대비
        String temp = loginUser.getFbUserImg(); // 이전 이미지 저장

        String rename = null; // 변경 이름 저장 변수

        // 업로드된 이미지가 있을 경우
        if(profileImg.getSize() > 0){
            // 파일 이름 변경
            rename = fileRename(profileImg.getOriginalFilename());

            // 바뀐 이름 loginuser에 세팅
            loginUser.setFbUserImg(webPath + rename);
        }else { // 없는 경우
            loginUser.setFbUserImg(null);
        }

        // 프로필 이미지 수정
        int result = sqlSession.update("UserMapper.updateProfile", loginUser);

        if (result > 0) { // 성공
            // 새 이미지가 업로드된 경우
            if (rename != null) {
                profileImg.transferTo(new File(filePath + webPath + rename));
            }
        }else { // 실패
            // 이전 이미지로 프로필 다시 세팅
            loginUser.setFbUserImg(temp);
        }
    }

    // 파일명 변경 메소드
    public static String fileRename(String originFileName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(new java.util.Date(System.currentTimeMillis()));

        int ranNum = (int) (Math.random() * 100000); // 5자리 랜덤 숫자 생성

        String str = "_" + String.format("%05d", ranNum);

        String ext = originFileName.substring(originFileName.lastIndexOf("."));

        return date + str + ext;
    }
}
