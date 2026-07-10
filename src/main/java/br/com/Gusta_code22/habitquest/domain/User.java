package br.com.Gusta_code22.habitquest.domain;

import br.com.Gusta_code22.habitquest.dto.LoginRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Table(name = "\"user\"")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Integer xp = 0;

    @Column
    private Integer level = 1;

    @OneToMany(mappedBy = "user")
    private List<Habit> habits;

    public boolean isLoginCorrect(LoginRequest request, BCryptPasswordEncoder encoder){
        return encoder.matches(request.password(), this.password);
    }

}
