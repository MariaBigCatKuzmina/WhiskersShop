package ru.kuzmina.whiskersshop.api.dtos;

public class JwtResponse {
    private String token;
    private String role;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse() {
    }
}
