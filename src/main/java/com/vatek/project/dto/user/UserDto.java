package com.catdev.project.dto.user;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String identityCard;
    private String phoneNumber1;
    private String phoneNumber2;
    private String currentAddress;
    private String permanentAddress;
    private String description;
    private boolean isEnabled;
}
