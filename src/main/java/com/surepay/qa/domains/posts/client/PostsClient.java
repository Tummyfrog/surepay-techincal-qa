package com.surepay.qa.domains.posts.client;

import com.surepay.qa.core.client.BaseClient;
import com.surepay.qa.core.utils.JsonUtils;
import com.surepay.qa.domains.posts.assertions.PostsAssertions;
import com.surepay.qa.domains.posts.model.Post;
import com.surepay.qa.support.constants.ApiPaths;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class PostsClient extends BaseClient {

    public List<Post> getPostsByUserId(int userId) {
        Response response = get(ApiPaths.POSTS, Map.of("userId", userId));
        PostsAssertions.assertStatusCode(response, 200);
        List<Post> posts = JsonUtils.fromJsonList(response.asString(), Post.class);

        PostsAssertions.assertPostTitleNotEmpty(posts);
        return posts;
    }

}
