package com.rb.auth.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDTO(@NotBlank String userId, String login, Long roleId) {
}
