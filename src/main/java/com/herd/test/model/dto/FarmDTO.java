package com.herd.test.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FarmDTO {

    private int id;
    private String farmName;

}
