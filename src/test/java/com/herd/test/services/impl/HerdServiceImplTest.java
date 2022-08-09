package com.herd.test.services.impl;

import com.herd.test.model.dto.HerdCreateUpdateDTO;
import com.herd.test.model.dto.HerdDTO;
import com.herd.test.model.entities.Herd;
import com.herd.test.repository.HerdRepository;
import com.herd.test.services.HerdService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class HerdServiceImplTest {

    private HerdService herdService;
    @Autowired
    private HerdRepository herdRepository;

    @BeforeEach
    void setUp() {
        herdService = new HerdServiceImpl(herdRepository);
    }

    @AfterEach
    void tearDown(){
        herdRepository.deleteAll();
    }

    @Test
    void canGetAllCows() {
        final List<HerdDTO> herdDTOList = herdService.getAllCows();
        final List<Herd> herdList = herdRepository.findAll();
        assertThat(herdDTOList.size()).isEqualTo(herdList.size());
    }

    @Test
    void canCreateNewCow() {
        final HerdCreateUpdateDTO herdCreateUpdateDTO = herdCreateUpdateDTO();
        int id = herdService.createNewCow(herdCreateUpdateDTO).getId();
        final Herd herdRepo = herdRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found!"));
        assertThat(herdRepo.getCollarId()).isEqualTo(herdCreateUpdateDTO.getCollarId());
    }

    @Test
    void updateCow() {
        final HerdCreateUpdateDTO herdCreateUpdateDTO = herdCreateUpdateDTO();
        herdCreateUpdateDTO.setCowNumber(35);
        final HerdDTO herdUpdatedCow = herdService.updateCow(1, herdCreateUpdateDTO);
        final Herd herd = herdRepository.findById(herdUpdatedCow.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found!"));
        assertThat(herd.getCowNumber()).isEqualTo(herdUpdatedCow.getCowNumber());
    }

    @Test
    void deleteCow() {
        herdRepository.deleteById(1);
        try {
            herdService.deleteCow(1);
        } catch (Exception e) {
            assertThatThrownBy(() ->  herdService.deleteCow(1));
        }
    }

    public HerdCreateUpdateDTO herdCreateUpdateDTO() {
        return HerdCreateUpdateDTO.builder()
                .cowNumber(456789)
                .collarId(5678923)
                .collarStatus("0")
                .build();
    }

}