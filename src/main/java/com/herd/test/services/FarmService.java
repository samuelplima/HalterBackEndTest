package com.herd.test.services;

import com.herd.test.model.dto.FarmCreateUpdateDTO;
import com.herd.test.model.dto.FarmDTO;
import com.herd.test.model.dto.HerdCreateUpdateDTO;
import com.herd.test.model.dto.HerdDTO;

import java.util.List;

public interface FarmService {

    List<FarmDTO> getAllFarms();

    FarmDTO createNewFarm(final FarmCreateUpdateDTO farmCreateUpdateDTO);

    FarmDTO updateFarm(final Integer id, final FarmCreateUpdateDTO farmCreateUpdateDTO);

    FarmDTO findFarmById(final int id);

    List<FarmDTO> findFarmByName(final String farmName);

    void deleteFarm(final Integer id);

}
