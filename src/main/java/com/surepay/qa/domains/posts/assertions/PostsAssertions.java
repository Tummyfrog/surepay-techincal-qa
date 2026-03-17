package com.surepay.qa.domains.posts.assertions;

import com.surepay.qa.domains.posts.model.Post;
import io.restassured.response.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public final class PostsAssertions {

    public PostsAssertions() {
    }

    public static void assertStatusCode(Response response, int expectedStatusCode) {
        assertThat(response.statusCode())
                .withFailMessage("Expected status code %s but found %s", expectedStatusCode, response.statusCode())
                .isEqualTo(expectedStatusCode);
    }

    public static void assertPostTitleNotEmpty(List<Post> posts) {
        assertThat(posts)
                .withFailMessage("Expected at least one post but found %s", posts.size())
                .isNotEmpty();
    }

}
