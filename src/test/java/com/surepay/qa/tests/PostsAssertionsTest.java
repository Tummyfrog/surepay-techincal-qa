package com.surepay.qa.tests;

import com.surepay.qa.domains.posts.assertions.PostsAssertions;
import com.surepay.qa.domains.posts.model.Post;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PostsAssertionsTest {

    @Test
    void shouldFailWhenPostsContainDuplicateIds() {
        Post first = new Post();
        first.setId(1);
        first.setUserId(9);

        Post duplicate = new Post();
        duplicate.setId(1);
        duplicate.setUserId(9);

        assertThatThrownBy(() -> PostsAssertions.assertNoDuplicatePosts(List.of(first, duplicate)))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void shouldFailWhenPostBelongsToDifferentUser() {
        Post post = new Post();
        post.setId(1);
        post.setUserId(8);

        assertThatThrownBy(() -> PostsAssertions.assertPostsBelongToUser(List.of(post), 9))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void shouldFailWhenPostListIsEmpty() {
        assertThatThrownBy(() -> PostsAssertions.assertPostTitleNotEmpty(List.of()))
                .isInstanceOf(AssertionError.class);
    }
}
