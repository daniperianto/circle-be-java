package com.ruangmain.cirle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String image;
    private String content;

    @CreatedDate
    @Builder.Default
    private Date created_at = new Date();

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    private List<Like> likes;
}
