package com.recruitment.rest;

import com.recruitment.rest.model.RequestsCount;
import com.recruitment.rest.service.RequestsCountService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RequestsCountService requestsCountService;

    @Test
    public void getUser() throws Exception {
        given(requestsCountService.getRequestsCountById("octocat")).willReturn(new RequestsCount("octocat", 15));
        mvc.perform(get("/users/{login}", "octocat")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login", is("octocat")))
                .andExpect(jsonPath("$.name", is("The Octocat")));
    }
}