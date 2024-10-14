package github.tanishqtrivedi27.userService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserInfo {
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @Id
    private String userId;

    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
}
