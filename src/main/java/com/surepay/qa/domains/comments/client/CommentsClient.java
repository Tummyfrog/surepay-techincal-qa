package com.surepay.qa.domains.comments.client;

import com.surepay.qa.core.client.BaseClient;
import com.surepay.qa.core.utils.JsonUtils;
import com.surepay.qa.domains.comments.assertions.CommentsAssertions;
import com.surepay.qa.domains.comments.model.Comment;
import com.surepay.qa.domains.posts.model.Post;
import com.surepay.qa.support.constants.ApiPaths;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentsClient extends BaseClient {

    public List<Comment> getCommentsByPostId(int postId) {
        Response response = get(ApiPaths.COMMENTS, Map.of("postId", postId));
        CommentsAssertions.assertStatusCode(response, 200);
        return JsonUtils.fromJsonList(response.asString(), Comment.class);
    }

    public List<Comment> getCommentsForPosts(List<Post> posts) {
        List<Comment> allComments = new ArrayList<>();
        for (Post post : posts) {
            List<Comment> comments = getCommentsByPostId(post.getId());
            allComments.addAll(comments);
        }

        return allComments;
    }
}
