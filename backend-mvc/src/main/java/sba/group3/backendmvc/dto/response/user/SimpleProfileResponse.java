package sba.group3.backendmvc.dto.response.user;

import sba.group3.backendmvc.entity.user.UserProfile;

import java.io.Serializable;

/**
 * DTO for {@link UserProfile}
 */
public record SimpleProfileResponse(String fullName, String avatarUrl) implements Serializable {
}