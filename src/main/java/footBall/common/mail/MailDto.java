package footBall.common.mail;

import lombok.Data;

@Data
public class MailDto {

    private String title;
    private String recipient;
    private String content;
}
