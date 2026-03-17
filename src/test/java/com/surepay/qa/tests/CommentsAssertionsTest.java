package com.surepay.qa.tests;

import com.surepay.qa.domains.comments.assertions.CommentsAssertions;
import com.surepay.qa.domains.comments.model.Comment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommentsAssertionsTest {

    @Test
    void shouldFailWhenCommentEmailIsNull() {
        Comment comment = new Comment();
        comment.setEmail(null);

        assertThatThrownBy(() -> CommentsAssertions.assertEmailFormat(comment))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void shouldFailWhenCommentEmailIsBlank() {
        Comment comment = new Comment();
        comment.setEmail("");

        assertThatThrownBy(() -> CommentsAssertions.assertEmailFormat(comment))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void shouldFailWhenCommentEmailFormatIsInvalid() {
        Comment comment = new Comment();
        comment.setEmail("not-an-email");

        assertThatThrownBy(() -> CommentsAssertions.assertEmailFormat(comment))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void shouldFailWhenCommentsBelongToDifferentPost() {
        Comment comment = new Comment();
        comment.setPostId(99);

        assertThatThrownBy(() -> CommentsAssertions.assertCommentsBelongToPost(List.of(comment), 1))
                .isInstanceOf(AssertionError.class);
    }
}
