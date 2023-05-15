package com.umbrella.dto.post;

import com.umbrella.domain.Post.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostListResponseDto {

    private Long id;

    private String writer;

    private String title;

    private Long likeCount;

    public PostListResponseDto(Post post){
        this.id = post.getId();
        this.writer = post.getWriter();
        this.title = post.getTitle();
        this.likeCount = Long.valueOf(post.getLikeCount());


    }

}
