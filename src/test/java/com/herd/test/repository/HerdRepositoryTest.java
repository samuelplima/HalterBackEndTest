package com.herd.test.repository;

import com.herd.test.model.entities.Herd;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HerdRepositoryTest {

    @Autowired
    private HerdRepository herdRepository;

    @AfterEach
    void tearDown(){
        herdRepository.deleteAll();;
    }

    public Herd herdBuilder() {
        return Herd.builder()
                .cowNumber(456789)
                .collarId(5678923)
                .collarStatus("0")
                .build();
    }

    @Test
    void itShouldCheckIfCowExitsByCowNumber() {
        herdRepository.save(herdBuilder());
        int cowNumber = herdRepository.findCowByNumber(herdBuilder().getCowNumber()).getCowNumber();
        assertThat(cowNumber).isEqualTo(herdBuilder().getCowNumber());
    }

    @Test
    void itShouldCheckIfCowExistsByCollarId() {
        herdRepository.save(herdBuilder());
        int collarId = herdRepository.findCollarIdByNumber(herdBuilder().getCollarId()).getCollarId();
        assertThat(collarId).isEqualTo(herdBuilder().getCollarId());
    }
}