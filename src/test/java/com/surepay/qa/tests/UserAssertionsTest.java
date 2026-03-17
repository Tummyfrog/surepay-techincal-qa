package com.surepay.qa.tests;

import com.surepay.qa.domains.users.assertions.UserAssertions;
import com.surepay.qa.domains.users.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserAssertionsTest {

    @Test
    void shouldFailWhenExpectedSingleUserButNoneFound() {
        assertThatThrownBy(() -> UserAssertions.assertUserCount(List.of(), 1))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void shouldFailWhenExpectedSingleUserButMultipleFound() {
        User first = new User();
        User second = new User();

        assertThatThrownBy(() -> UserAssertions.assertUserCount(List.of(first, second), 1))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void shouldFailWhenUsernameDoesNotMatch() {
        User user = new User();
        user.setUsername("Bret");

        assertThatThrownBy(() -> UserAssertions.assertUsername(user, "Delphine"))
                .isInstanceOf(AssertionError.class);
    }
}
