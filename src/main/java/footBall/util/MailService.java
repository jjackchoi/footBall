package footBall.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    //단순 문자 메일 보내기
    public void sendSimpleEmail(MailDto mailDto) {
        //단순 문자 메일을 보낼 수 있는 객체 생성
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mailDto.getTitle()); // 메일 제목
        message.setTo(mailDto.getRecipient()); // 수신자
        message.setText(mailDto.getContent()); // 내용

        javaMailSender.send(message);
    }

    // 6자리 랜덤 비밀번호 생성
    public String createRandomPw() {
        // 문자 배열에 넣기
        String[] StringSet = new String[]{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        };

        // 랜덤한 임시 비밀번호 변수
        String pwd = "";

        // 문자 배열 길이의 값을 랜덤으로 6개를 뽑아 조합
        int randomIndex = 0;
        for (int i = 0; i < 6; i++){
            randomIndex = (int)(StringSet.length * Math.random());
            pwd += StringSet[randomIndex];
        }

        return pwd;
    }

}
