package com.jolly.saml.controllers;

import com.jolly.saml.CommonTestSupport;
import com.jolly.saml.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class SSOControllerTest extends CommonTestSupport {

    private static final Set<String> IDPS =
            Collections.unmodifiableSet(
            		new HashSet<>(Arrays.asList("idp1", "idp2", "idp3")));

    @InjectMocks
    SSOController ssoController;

    @Mock
    private MetadataManager metadata;

    @Mock
    private View mockView;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp()
    {
        mockMvc = standaloneSetup(ssoController).setSingleView(mockView).build();
    }

    @Test
    @WithMockUser
    public void testIdpSelectionWithUser() throws Exception {
        mockMvc.perform(get("/saml/discovery"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/landing"));
    }

    @Test
    public void testIdpSelection() throws Exception {
        // given
        when(metadata.getIDPEntityNames()).thenReturn(IDPS);

        // when / then
        mockMvc.perform(get("/saml/discovery").session(mockAnonymousHttpSession()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("idps", IDPS))
                .andExpect(view().name("pages/discovery"));
    }

}
