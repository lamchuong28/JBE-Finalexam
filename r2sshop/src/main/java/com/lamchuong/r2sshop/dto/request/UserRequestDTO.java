package com.lamchuong.r2sshop.dto.request;

import com.lamchuong.r2sshop.model.User;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String username;
    private String password;
    private String email;
    private String fullName;

    public User toUser() {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setFullName(this.fullName);
        return user;
    }
}
