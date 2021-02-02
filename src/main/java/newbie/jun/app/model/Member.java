package newbie.jun.app.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="Member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(length = 50,name="user_email")
    private String email;

    @NotNull
    @Column(length = 20,name="user_name")
    private String name;

    @NotNull
    @Column(length = 20,name="user_nickname")
    private String nickname;

    @NotNull
    @Column(length = 80,name="user_password")
    private String password;

    @Column(name="user_role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_NOT_PERMITTED;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateAt;

    @Builder
    public Member(String email, String name, String nickname, String password){
        this.email=email;
        this.name=name;
        this.nickname=nickname;
        this.password=password;
    }
}
