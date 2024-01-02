package footBall.user;

import java.util.List;

public interface UserService {
    int userRegister(UserRequest userRequest);

    List<UserResponse> userAllFind();
}
