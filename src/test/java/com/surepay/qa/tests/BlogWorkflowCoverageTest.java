package com.surepay.qa.tests;

import com.surepay.qa.domains.comments.assertions.CommentsAssertions;
import com.surepay.qa.domains.comments.client.CommentsClient;
import com.surepay.qa.domains.comments.model.Comment;
import com.surepay.qa.domains.posts.assertions.PostsAssertions;
import com.surepay.qa.domains.posts.client.PostsClient;
import com.surepay.qa.domains.posts.model.Post;
import com.surepay.qa.domains.users.assertions.UserAssertions;
import com.surepay.qa.domains.users.client.UsersClient;
import com.surepay.qa.domains.users.model.User;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

class BlogWorkflowCoverageTest {

    private final UsersClient usersClient = new UsersClient();
    private final PostsClient postsClient = new PostsClient();
    private final CommentsClient commentsClient = new CommentsClient();

    @Test
    @Tag("regression")
    @Description("Verify that Delphine can be found exactly once")
    void shouldFindSingleUserForDelphine() {
        List<User> users = usersClient.getUsersByUsername("Delphine");

        UserAssertions.assertUserCount(users, 1);
        UserAssertions.assertUsername(users.getFirst(), "Delphine");
    }

    @Test
    @Tag("regression")
    @Description("Verify that unknown usernames return no users")
    void shouldReturnNoUsersForUnknownUsername() {
        List<User> users = usersClient.getUsersByUsername("unknown-user-for-surepay-qa");

        UserAssertions.assertUserCount(users, 0);
    }

    @Test
    @Tag("regression")
    @Description("Verify that Delphine posts are present, unique, and owned by the requested user")
    void shouldReturnPostsOwnedByDelphineWithoutDuplicates() {
        User user = usersClient.getSingleUsersByUsername("Delphine");
        List<Post> posts = postsClient.getPostsByUserId(user.getId());

        PostsAssertions.assertPostTitleNotEmpty(posts);
        PostsAssertions.assertNoDuplicatePosts(posts);
        PostsAssertions.assertPostsBelongToUser(posts, user.getId());
    }

    @Test
    @Tag("regression")
    @Description("Verify that comments fetched for Delphine posts are present and belong to the requested post")
    void shouldReturnCommentsForEveryDelphinePost() {
        User user = usersClient.getSingleUsersByUsername("Delphine");
        List<Post> posts = postsClient.getPostsByUserId(user.getId());

        for (Post post : posts) {
            List<Comment> comments = commentsClient.getCommentsByPostId(post.getId());

            CommentsAssertions.assertCommentsNotEmpty(comments);
            CommentsAssertions.assertCommentsBelongToPost(comments, post.getId());
        }
    }
}
