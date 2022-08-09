package com.herd.test.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HerdDTO {

    private int id;
    private int collarId;
    private int cowNumber;
    private String collarStatus;


}
