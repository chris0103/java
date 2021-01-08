package org.chris.study.template;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PharmLocatorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testController() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/search").accept(MediaType.TEXT_HTML)).andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}
