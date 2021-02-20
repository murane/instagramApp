package newbie.jun.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import io.jsonwebtoken.lang.Assert;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="member")
public class Member{
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 50,name="user_email",nullable = false, unique = true)
    private String email;

    @Column(length = 20,name="user_name",nullable = false)
    private String name;

    @Column(length = 20,name="user_nickname",nullable = false, unique = true)
    private String nickname;

    @Column(length = 80,name="user_password",nullable = false)
    private String password;

    @Column(name="user_role")
    @Enumerated(EnumType.STRING)
    private final UserRole role = UserRole.ROLE_NOT_PERMITTED;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateAt;

    //내가 팔로우하는 사람들
    //@JoinColumn("")
    @JsonIgnore
    //팔로워가 '나'인 경우
    @OneToMany(mappedBy = "follower",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Follow> followings;

    //나를 팔로우하는사람들
    //@JoinColumn("")
    @JsonIgnore
    //팔로이가 '나'인 경우
    @OneToMany(mappedBy = "followee",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Follow> followers;


    @Builder
    public Member(String email, String name, String nickname, String password){
        Assert.hasText(email, "email must not be empty");
        Assert.hasText(name, "name must not be empty");
        Assert.hasText(nickname, "nickname must not be empty");
        Assert.hasText(password, "password must not be empty");

        this.email=email;
        this.name=name;
        this.nickname=nickname;
        this.password=password;
    }
}
