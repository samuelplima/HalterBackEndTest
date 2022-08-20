package com.herd.test.helper;

import com.herd.test.model.dto.FarmCreateUpdateDTO;
import com.herd.test.model.dto.FarmDTO;
import com.herd.test.model.entities.Farm;

public class FarmHelper {

    public static Farm farmBuilderHerdCreation(final int farmId, final String farmName){
        return Farm.builder()
                .farmName(farmName)
                .id(farmId)
                .build();
    }

    public static FarmDTO farmDTOBuilder(final Farm farm) {
        return FarmDTO.builder()
                .id(farm.getId())
                .farmName(farm.getFarmName())
                .build();
    }

    public static Farm farmUpdateBuilder(final Farm farm, final FarmCreateUpdateDTO farmCreateUpdateDTO) {
        return Farm.builder()
                .id(farm.getId())
                .farmName(farmCreateUpdateDTO.getFarmName())
                .build();
    }

    public static Farm farmBuilder(final FarmCreateUpdateDTO farmCreateUpdateDTO) {
        return Farm.builder()
                .farmName(farmCreateUpdateDTO.getFarmName())
                .build();
    }

}
