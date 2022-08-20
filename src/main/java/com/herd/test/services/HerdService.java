package com.herd.test.services;

import com.herd.test.model.dto.HerdCreateUpdateDTO;
import com.herd.test.model.dto.HerdDTO;

import java.util.List;

public interface HerdService {

    List<HerdDTO> getAllCows();

    HerdDTO createNewCow(final HerdCreateUpdateDTO herdCreateUpdateDTO);

    HerdDTO updateCow(final Integer id, final HerdCreateUpdateDTO herdCreateUpdateDTO);

    List<HerdDTO> getAllCowsByFarmId(final int id);

    void deleteCow(final Integer id);

}
