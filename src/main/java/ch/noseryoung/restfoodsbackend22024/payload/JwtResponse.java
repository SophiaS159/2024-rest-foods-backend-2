package ch.noseryoung.restfoodsbackend22024.payload;

import ch.noseryoung.restfoodsbackend22024.model.User;
import lombok.Getter;
import lombok.Setter;

public class JwtResponse {

    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private Long userId;
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private User.Role role;

    public JwtResponse(String token, Long userId, String login, User.Role role) {
        this.token = token;
        this.userId = userId;
        this.login = login;
        this.role = role;
    }
}
