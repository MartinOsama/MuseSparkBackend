package com.martinosama.musespark.DTO;

import lombok.Getter;
import lombok.Setter;


public class UserDTO {
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String userRole;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String birthday;
    @Getter
    @Setter
    private String gender;
}