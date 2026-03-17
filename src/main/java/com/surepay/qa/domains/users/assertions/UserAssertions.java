package com.surepay.qa.domains.users.assertions;

import com.surepay.qa.domains.users.model.User;
import io.restassured.response.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public final class UserAssertions {

    private UserAssertions() {
    }

    public static void assertStatusCode(Response response, int expectedStatusCode) {
        assertThat(response.statusCode())
                .withFailMessage("Expected status code %s but was %s", expectedStatusCode, response.statusCode())
                .isEqualTo(expectedStatusCode);
    }

    public static void assertUserCount(List<User> users, int expectedCount) {
        assertThat(users)
                .withFailMessage("Expected %s user(s) but found %s", expectedCount, users.size())
                .hasSize(expectedCount);
    }

    public static void assertUsername(User user, String expectedUsername) {
        assertThat(user.getUsername()).isEqualTo(expectedUsername);
    }

    public static void assertName(User user, String expectedName) {
        assertThat(user.getName()).isEqualTo(expectedName);
    }

    public static void assertEmail(User user, String expectedEmail) {
        assertThat(user.getEmail()).isEqualTo(expectedEmail);
    }

    public static void assertCity(User user, String expectedCity) {
        assertThat(user.getAddress().getCity()).isEqualTo(expectedCity);
    }

    public static void assertCompanyName(User user, String expectedCompanyName) {
        assertThat(user.getCompany().getName()).isEqualTo(expectedCompanyName);
    }

    public static void assertPhone(User user, String expectedCompanyName) {
        assertThat(user.getPhone()).isEqualTo(expectedCompanyName);
    }
}