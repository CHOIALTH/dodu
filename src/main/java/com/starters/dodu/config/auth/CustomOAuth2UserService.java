package com.starters.dodu.config.auth;

import com.starters.dodu.domain.Mentee;
import com.starters.dodu.repository.MenteeRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MenteeRepository menteeRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        // 추가
        System.out.println("[oAuth2USer] : " + oAuth2User);
        System.out.println("[getAttribtues] : " + oAuth2User.getAttributes());

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        System.out.println("===LOG=== :: " + attributes.getGender());
        Mentee mentee = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(mentee));

        return new DefaultOAuth2User(
                //Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_MENTEE")),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private Mentee saveOrUpdate(OAuthAttributes attributes) {
        // 이메일이 이미 존재하면
        Mentee mentee = menteeRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getNickname(), attributes.getGender(), attributes.getMobile(), 2023 - Integer.parseInt(attributes.getBirthyear())))
                .orElse(attributes.toEntity());

        return menteeRepository.save(mentee);
    }
}
