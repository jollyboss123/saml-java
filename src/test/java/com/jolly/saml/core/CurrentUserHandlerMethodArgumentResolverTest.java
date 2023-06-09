package com.jolly.saml.core;

import com.jolly.saml.CommonTestSupport;
import com.jolly.saml.TestConfig;
import com.jolly.saml.annotations.CurrentUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.Principal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= TestConfig.class)
public class CurrentUserHandlerMethodArgumentResolverTest extends CommonTestSupport {

    @Autowired
    private CurrentUserHandlerMethodArgumentResolver resolver;
    
    private MethodParameter validParam;
    private MethodParameter notAnnotatedParam;
    private MethodParameter wrongTypeParam;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        validParam = new MethodParameter(
        		MethodSamples.class.getMethod("validUser", User.class), 0);
        notAnnotatedParam = new MethodParameter(
        		MethodSamples.class.getMethod("notAnnotatedUser", User.class), 0);
        wrongTypeParam = new MethodParameter(
        		MethodSamples.class.getMethod("wrongTypeUser", Object.class), 0);
    }

    @Test
    public void testSupportsParameter() throws NoSuchMethodException {
        assertTrue(resolver.supportsParameter(validParam));
        assertFalse(resolver.supportsParameter(notAnnotatedParam));
        assertFalse(resolver.supportsParameter(wrongTypeParam));
    }

    @Test
    public void testResolveArgument() throws Exception {
        // given
        ModelAndViewContainer mavContainer = mock(ModelAndViewContainer.class);
        WebDataBinderFactory binderFactory = mock(WebDataBinderFactory.class);
        NativeWebRequest webRequest = mock(NativeWebRequest.class);
        User stubUser = new User(USER_NAME, "", Collections.emptyList());
        Principal stubPrincipal = new UsernamePasswordAuthenticationToken(stubUser, null);
        when(webRequest.getUserPrincipal()).thenReturn(stubPrincipal);

        // when/then
        assertEquals(stubUser,
                resolver.resolveArgument(validParam, mavContainer, webRequest,binderFactory));
        assertEquals(WebArgumentResolver.UNRESOLVED,
                resolver.resolveArgument(notAnnotatedParam, mavContainer, webRequest,binderFactory));
        assertEquals(WebArgumentResolver.UNRESOLVED,
                resolver.resolveArgument(wrongTypeParam, mavContainer, webRequest,binderFactory));
    }

    @SuppressWarnings("unused")
    private static final class MethodSamples
    {
        public void validUser(@CurrentUser User user) {}
        public void notAnnotatedUser(User user) {}
        public void wrongTypeUser(@CurrentUser Object user) {}
    }
}
