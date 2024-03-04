package com.ruangmain.cirle.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "follows")
@IdClass(FollowPK.class)
public class Follow implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User following;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followers_id")
    private User followers;

}
