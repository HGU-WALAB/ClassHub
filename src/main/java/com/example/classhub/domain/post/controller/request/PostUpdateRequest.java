package com.example.classhub.domain.post.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class PostUpdateRequest {
    private String postTitle;
    private String postContent;
}
