package footBall.common.file;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileUtils {
    private final String uploadPath = Paths.get("C:", "develop", "upload-files").toString();


    /**
     * 단일 파일 업로드
     * @param multipartFile - 파일 객체
     * @return DB에 저장할 파일 정보
     */
//    public FileRequest uploadFile(final MultipartFile multipartFile) {
//
//        if (multipartFile.isEmpty()) {
//            return null;
//        }
//
//        String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
//        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
//        String uploadPath = getUploadPath(today) + File.separator + saveName;
//        File uploadFile = new File(uploadPath);
//
//        try {
//            multipartFile.transferTo(uploadFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return FileRequest.builder()
//                .originalName(multipartFile.getOriginalFilename())
//                .saveName(saveName)
//                .size(multipartFile.getSize())
//                .build();
//    }

    /**
     * 저장 파일명 생성
     * @param filename 원본 파일명
     * @return 디스크에 저장할 파일명
     */
    private String generateSaveFilename(final String filename) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(filename);
        return uuid + "." + extension;
    }

    /**
     * 업로드 경로 반환
     * @return 업로드 경로
     */
    private String getUploadPath() {
        return makeDirectories(uploadPath);
    }

    /**
     * 업로드 경로 반환
     * @param addPath - 추가 경로
     * @return 업로드 경로
     */
    private String getUploadPath(final String addPath) {
        return makeDirectories(uploadPath + File.separator + addPath);
    }

    /**
     * 업로드 폴더(디렉터리) 생성
     * @param path - 업로드 경로
     * @return 업로드 경로
     */
    private String makeDirectories(final String path) {
        File dir = new File(path);
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        return dir.getPath();
    }
}
