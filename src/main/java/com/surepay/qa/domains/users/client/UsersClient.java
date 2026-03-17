package com.surepay.qa.domains.users.client;

import com.surepay.qa.core.client.BaseClient;
import com.surepay.qa.core.utils.JsonUtils;
import com.surepay.qa.domains.users.assertions.UserAssertions;
import com.surepay.qa.domains.users.model.User;
import com.surepay.qa.support.constants.ApiPaths;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class UsersClient extends BaseClient {

    public User getSingleUsersByUsername(String username) {
        Response response = get(ApiPaths.USERS, Map.of("username", username));
        UserAssertions.assertStatusCode(response, 200);

        List<User> users = JsonUtils.fromJsonList(response.asString(), User.class);
        UserAssertions.assertUserCount(users, 1);

        User user = users.getFirst();
        UserAssertions.assertUsername(user, username);

        return user;
    }

}