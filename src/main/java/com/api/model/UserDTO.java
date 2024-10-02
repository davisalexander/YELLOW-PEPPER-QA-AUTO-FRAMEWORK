package com.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    public int id;
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phone;
    public int userStatus;
}
