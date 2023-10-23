package com.techtips.techtips.posts.model.entity;

import com.techtips.techtips.users.model.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    private LocalDateTime publishDate;
    private String image;
    private String status;
//    private List<String> tags;
//    private List<Comment> comments;
//    private int likes;
//    private int views;
//    private String permalink;

}
