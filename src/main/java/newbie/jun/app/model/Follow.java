package newbie.jun.app.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name="follow")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //팔로우 하는사람
    @ManyToOne
    @JoinColumn(name="follower_id",nullable = false)
    private Member follower;

    //팔로우 당하는 사람
    @ManyToOne
    @JoinColumn(name="followee_id",nullable = false)
    private Member followee;

    public Follow(Member follower,Member followee){
        this.follower=follower;
        this.followee=followee;
    }
}
