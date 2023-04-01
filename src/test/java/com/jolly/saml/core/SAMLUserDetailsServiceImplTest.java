package com.jolly.saml.core;

import com.jolly.saml.CommonTestSupport;
import com.jolly.saml.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.opensaml.saml2.core.NameID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= TestConfig.class)
public class SAMLUserDetailsServiceImplTest extends CommonTestSupport {

    @Autowired
    private SAMLUserDetailsServiceImpl userDetailsService;

    @Test
    public void testLoadUserBySAML() {
        // given
        NameID mockNameID = mock(NameID.class);
        when(mockNameID.getValue()).thenReturn(USER_NAME);

        SAMLCredential credentialsMock = mock(SAMLCredential.class);
        when(credentialsMock.getNameID()).thenReturn(mockNameID);

        // when
        Object actual = userDetailsService.loadUserBySAML(credentialsMock);

        // / then
        assertNotNull(actual);
        assertTrue(actual instanceof User);

        User user = (User)actual;
        assertEquals(USER_NAME, user.getUsername());
        assertEquals(USER_PASSWORD, user.getPassword());
        assertTrue(user.isEnabled());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertEquals(1, user.getAuthorities().size());

        List<GrantedAuthority> authorities = new ArrayList<>(user.getAuthorities());
        Object authority = authorities.get(0);

        assertTrue(authority instanceof SimpleGrantedAuthority);
        assertEquals(USER_ROLE, ((SimpleGrantedAuthority)authority).getAuthority());
    }
}
