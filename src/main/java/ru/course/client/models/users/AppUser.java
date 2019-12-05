package ru.course.client.models.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class AppUser {
    private String password;
    private Role role;
    private Long id;
    private String login;


}
