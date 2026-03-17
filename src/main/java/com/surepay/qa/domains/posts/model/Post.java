package com.surepay.qa.domains.posts.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Post {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
