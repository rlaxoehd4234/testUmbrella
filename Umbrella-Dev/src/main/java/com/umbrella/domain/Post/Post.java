package com.umbrella.domain.Post;

import com.umbrella.domain.Board.Board;
import com.umbrella.domain.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @NotNull
    @Column(length = 500)
    private String title;

    @NotNull
    @Size(min = 50, max= 1000)
    @Column(columnDefinition = "TEXT")
    private String content;

    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @ColumnDefault("0")
    private Integer likeCount;

    public Post(Long id, String title) {
        this.id = id;
        this.title = title;
    }


    @Builder
    public Post(String writer, String title, String content, User user, Board board){
        this.writer = writer;
        this.content = content;
        this.title = title;
        this.user = user;
        this.board = board;
    }


    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
    public void addHeart(){
        likeCount++;
    }
    public void popHeart() {
        likeCount--;
    }
}