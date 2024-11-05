package bg.soft_uni.mobilelelele.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void registerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-register"))
                .andExpect(model().attributeExists("registerSeedDto"));
    }
    @Test
    public void loginTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-login"))
                .andExpect(model().attributeExists("loginDto"));
    }
    @Test
    public void addOfferTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .with(csrf())
                        .param("firstName", "Rosen")
                        .param("lastName", "Tod ")
                        .param("email", "rosen.comp28@gmail.com")
                        .param("password", "12345"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users/login"));
    }
    @Test
    public void addOfferInvalidTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .with(csrf())
                        .param("firstName", "Rosen")
                        .param("lastName", "Tod ")
                        .param("email", "ros")
                        .param("password", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users/register"));
    }
}
