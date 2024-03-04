package com.ruangmain.cirle.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "threads")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Thread implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-seq")
    @SequenceGenerator(name = "custom-seq", sequenceName = "threads_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String image;
    private String content;
    private Date created_at = new Date();

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    private List<Like> likes;
}
