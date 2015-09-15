package ru.javawebinar.topjava.web.user;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class AdminRestControllerTest extends AbstractControllerTest {

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(AdminRestController.REST_URL + '/'+(START_SEQ + 1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}