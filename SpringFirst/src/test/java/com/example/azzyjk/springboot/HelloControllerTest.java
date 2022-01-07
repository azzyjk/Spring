package com.example.azzyjk.springboot;

import com.example.azzyjk.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello_getHello_statusOk() throws Exception {
        // given
        String hello = "hello";

        // when
        ResultActions resultActions = mvc.perform(get("/hello"));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto() throws Exception {
        // given
        String name = "hello";
        int amount = 1000;

        // when
        ResultActions resultActions = mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath( "$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
