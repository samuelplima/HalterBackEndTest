package com.herd.test.controller;

import com.herd.test.model.dto.HerdCreateUpdateDTO;
import com.herd.test.model.dto.HerdDTO;
import com.herd.test.services.HerdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class HerdControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HerdService herdService;

    @Test
    void getAllCows() throws Exception {
        List<HerdDTO> herdDTOList = new ArrayList<>();
        herdDTOList.add(herdDTO());
        when(herdService.getAllCows())
                .thenReturn(herdDTOList);
        mvc.perform(get("/herd/getAllCows"))
                .andExpect(status().isOk());
    }

    @Test
    void createCow() throws Exception {
        when(herdService.createNewCow(herdCreateUpdateDTO()))
                .thenReturn(herdDTO());
        mvc.perform(post("/herd/createCow")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"collarId\":5678923,\"cowNumber\":456789,\"collarStatus\":\"0\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateCow() throws Exception {
        when(herdService.updateCow(1, herdCreateUpdateDTO()))
                .thenReturn(herdDTO());
        mvc.perform(put("/herd/updateCow/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"collarId\":5678923,\"cowNumber\":456789,\"collarStatus\":\"0\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCow() throws Exception {
        mvc.perform(delete("/herd/deleteCow/1"))
                .andExpect(status().isOk());
    }


    public HerdCreateUpdateDTO herdCreateUpdateDTO() {
        return HerdCreateUpdateDTO.builder()
                .cowNumber(456789)
                .collarId(5678923)
                .collarStatus("0")
                .build();
    }

    public HerdDTO herdDTO() {
        return HerdDTO.builder()
                .id(1)
                .cowNumber(456789)
                .collarId(5678923)
                .collarStatus("0")
                .build();
    }
}