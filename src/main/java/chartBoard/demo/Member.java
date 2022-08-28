package chartBoard.demo;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import static java.lang.reflect.Modifier.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Column(nullable = false)
    private String username;


    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateUsername(String username) {
        this.username = username;
    }
}
