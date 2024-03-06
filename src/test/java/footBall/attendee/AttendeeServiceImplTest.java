package footBall.attendee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AttendeeServiceImplTest {

    @Autowired
    private AttendeeServiceImpl attendeeService;
    @Test
    void dateCreate() {
//        AttendeeDto dto = new AttendeeDto();
//        LocalDateTime currentDate = LocalDateTime.now();
//        dto.setVoteDate(currentDate);
//        System.out.println(dto.toString());
//        attendeeService.createDate(dto);
    }
}