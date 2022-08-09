package com.herd.test.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HerdCreateUpdateDTO {

    private int collarId;
    private int cowNumber;
    private String collarStatus;

}
