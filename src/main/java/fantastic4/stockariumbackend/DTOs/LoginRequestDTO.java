package fantastic4.stockariumbackend.DTOs;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    //ChatGPT usaba username, prefiero email para login
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
