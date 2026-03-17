package com.surepay.qa.domains.comments.assertions;

import com.surepay.qa.domains.comments.model.Comment;
import io.restassured.response.Response;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public final class CommentsAssertions {

    private CommentsAssertions() {
    }

    public static void assertStatusCode(Response response, int expectedStatusCode) {
        assertThat(response.statusCode())
                .withFailMessage("Expected status code %s but found %s", expectedStatusCode, response.statusCode())
                .isEqualTo(expectedStatusCode);
    }

    public static void assertCommentsNotEmpty(List<Comment> comments) {
        assertThat(comments)
                .withFailMessage("Comment list is empty")
                .isNotEmpty();
    }

    public static void assertEmailFormat(Comment comment) {
        assertThat(EmailValidator.getInstance().isValid(comment.getEmail()))
                .withFailMessage("Invalid email format %s",  comment)
                .isTrue();
    }

    public static void assertNameFormat(Comment comment) {
        assertThat(comment.getName().contains("a") || comment.getName().contains("e"))
                .withFailMessage("Invalid name format %s",  comment)
                .isTrue();
    }

    public static void assertCommentsBelongToPost(List<Comment> comments, int expectedPostId) {
        assertThat(comments)
                .withFailMessage("Expected all comments to belong to post id %s but found %s", expectedPostId, comments)
                .allMatch(comment -> comment.getPostId() == expectedPostId);
    }
}
