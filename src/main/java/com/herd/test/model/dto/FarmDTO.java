package com.herd.test.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Builder
@Data
public class FarmDTO {

    private int id;
    private String farmName;

}
