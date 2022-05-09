package com.mangkyu.requestbody;

import com.google.gson.Gson;
import com.mangkyu.requestbody.app.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomRequestbodyApplicationTests {

    @Autowired
    private Gson gson;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 호출성공() throws Exception {
        // given
        final String url = "/users";

        final User user = new User();
        user.setName("test-name");
        user.setDesc("test-desc");

        // when
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(new Gson().toJson(user))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect(status().isOk())
                        .andExpect(jsonPath("name").value("test-name"))
                        .andExpect(jsonPath("desc").value("desc"));
    }

    @Test
    void 호출실패_유효성에러() throws Exception {
        // given
        final String url = "/users";

        final User user = new User();
        user.setName(null);
        user.setDesc(null);

        // when
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(new Gson().toJson(user))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect(status().isBadRequest());
    }

}
