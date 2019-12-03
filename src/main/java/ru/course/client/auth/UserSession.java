package ru.course.client.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.course.client.models.users.AppUser;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSession {
    private AppUser appUser;
}
