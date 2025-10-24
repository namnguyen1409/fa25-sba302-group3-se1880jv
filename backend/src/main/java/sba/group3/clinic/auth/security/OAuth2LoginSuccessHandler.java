package sba.group3.clinic.auth.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import sba.group3.clinic.auth.entity.OAuthAccount;
import sba.group3.clinic.auth.repository.OAuthAccountRepository;
import sba.group3.clinic.common.enums.OAuthProvider;
import sba.group3.clinic.user.entity.User;
import sba.group3.clinic.user.repository.UserRepository;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    OAuthAccountRepository oAuthAccountRepository;
    UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        var oAuth2User = (OAuth2User) authentication.getPrincipal();

        String providerId = (String) oAuth2User.getAttributes().get("sub"); // google
        if (providerId == null)
            providerId = (String) oAuth2User.getAttributes().get("id"); // facebook

        var email = (String) oAuth2User.getAttributes().get("email");
        var name = (String) oAuth2User.getAttributes().get("name");
        var avatar = (String) oAuth2User.getAttributes().get("picture");

        OAuthProvider provider = getProviderFromRequest(request);

        var existing = oAuthAccountRepository.findByProviderAndProviderUserId(provider, providerId);
        User user;

        if (existing.isPresent()) {
            user = existing.get().getUser();
        } else {
            user = userRepository.findByEmail(email).orElseGet(() ->
                    userRepository.save(
                            User.builder()
                                    .email(email)
                                    .password(UUID.randomUUID().toString())
                                    .active(true)
                                    .firstLogin(true)
                                    .build() // ✅ cần có
                    )
            );

            var account = new OAuthAccount();
            account.setUser(user);
            account.setProvider(provider);
            account.setProviderUserId(providerId);
            account.setEmail(email);
            account.setName(name);
            account.setAvatarUrl(avatar);
            oAuthAccountRepository.save(account);
        }

        // TODO: sinh JWT và redirect
        response.sendRedirect("http://localhost:5173/oauth-success");
    }

    private OAuthProvider getProviderFromRequest(HttpServletRequest request) {
        if (request.getRequestURI().contains("google")) return OAuthProvider.GOOGLE;
        if (request.getRequestURI().contains("facebook")) return OAuthProvider.FACEBOOK;
        return OAuthProvider.GITHUB;
    }
}
