package com.example.demo.controller;

import com.example.demo.dto.HistoryDTO;
import com.example.demo.model.History;
import com.example.demo.service.HistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HistoryController.class)
class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryService historyService;

    @Test
    void getAllHistorys() {
    }

    @Test
    void getHistoryById() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/patHistory/id?id=1"))
                .andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        //Assert
        assertEquals(200, status);

        verify(historyService, times(1)).findHistoryById(any(String.class));
    }

    @Test
    void addHistory() throws Exception {
//        HistoryDTO historyDTO = mock(HistoryDTO.class);
        HistoryDTO historyDTO = new HistoryDTO();

        when(historyService.addHistory(historyDTO)).thenReturn(any(History.class));

        this.mockMvc.perform(post("/patHistory/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("idConsultation: null\n" +
                                "idHistory: \"45\"\n" +
                                "observations: \"test\"\n" +
                                "recommendations: \"test\"")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

//        verify(historyService, times(1)).addHistory(any(HistoryDTO.class));
    }

    @Test
    void updateHistory() throws Exception {
    }

    @Test
    void deleteHistory() throws Exception {
    }

    @Test
    void getHistoryService() throws Exception {
    }

    @Test
    void testEquals() throws Exception {
    }

    @Test
    void canEqual() throws Exception {
    }

    @Test
    void testHashCode() throws Exception {
    }

    @Test
    void testToString() throws Exception {
    }
}
