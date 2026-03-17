package com.surepay.qa.tests;

import com.surepay.qa.domains.comments.assertions.CommentsAssertions;
import com.surepay.qa.domains.comments.client.CommentsClient;
import com.surepay.qa.domains.comments.model.Comment;
import com.surepay.qa.domains.posts.client.PostsClient;
import com.surepay.qa.domains.posts.model.Post;
import com.surepay.qa.domains.users.client.UsersClient;
import com.surepay.qa.domains.users.model.User;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

public class VerifyEmailFormatTest {

    private final UsersClient usersClient = new UsersClient();
    private final PostsClient postsClient = new PostsClient();
    private final CommentsClient commentsClient = new CommentsClient();

    @Test
    @Tag("smoke")
    @Tag("regression")
    @Description("Verify that emails for comments on Delphine's posts have the correct format")
    void shouldReturnCommentsWithValidEmailFormatForDelphinePosts() {
        User user = usersClient.getSingleUsersByUsername("Delphine");
        List<Post> posts = postsClient.getPostsByUserId(user.getId());
        List<Comment> comments = commentsClient.getCommentsForPosts(posts);
        comments.forEach(CommentsAssertions::assertEmailFormat);
    }

}