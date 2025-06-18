package ch.noseryoung.restfoodsbackend22024.DTO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginRequest {
    private String login;    // hier kommt entweder username oder email rein
    private String password;
}

