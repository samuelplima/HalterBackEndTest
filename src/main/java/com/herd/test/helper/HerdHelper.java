package com.herd.test.helper;

import com.herd.test.enuns.HerdEnum;
import com.herd.test.exception.RestBusinessException;
import com.herd.test.model.dto.HerdCreateUpdateDTO;
import com.herd.test.model.dto.HerdDTO;
import com.herd.test.model.entities.Herd;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class HerdHelper {

    public static HerdDTO herdDTOBuilder(final Herd herd) {
        return HerdDTO.builder()
                .id(herd.getId())
                .collarId(herd.getCollarId())
                .cowNumber(herd.getCowNumber())
                .collarStatus(herd.getCollarStatus())
                .build();
    }

    public static Herd herdUpdateBuilder(final Herd herd, final HerdCreateUpdateDTO herdCreateUpdateDTO) {
        return Herd.builder()
                .id(herd.getId())
                .collarId(herdCreateUpdateDTO.getCollarId())
                .cowNumber(herdCreateUpdateDTO.getCowNumber())
                .collarStatus(herdEnumBuilder(herdCreateUpdateDTO))
                .build();
    }

    public static Herd herdBuilder(final HerdCreateUpdateDTO herdCreateUpdateDTO) {
        return Herd.builder()
                .collarId(herdCreateUpdateDTO.getCollarId())
                .cowNumber(herdCreateUpdateDTO.getCowNumber())
                .collarStatus(herdEnumBuilder(herdCreateUpdateDTO))
                .build();
    }

    public static String herdEnumBuilder(final HerdCreateUpdateDTO herdCreateUpdateDTO) {
        HerdEnum herdEnum = null;

        if (Objects.equals(herdCreateUpdateDTO.getCollarStatus(), "0")) {
            herdEnum = HerdEnum.ACTIVATED;
        }
        if (Objects.equals(herdCreateUpdateDTO.getCollarStatus(), "1")) {
            herdEnum = HerdEnum.DEACTIVATED;
        }
        if (Objects.equals(herdCreateUpdateDTO.getCollarStatus(), "2")) {
            herdEnum = HerdEnum.DEFECTIVE;
        }

        if (Objects.isNull(herdEnum)) {
            throw new RestBusinessException(HttpStatus.BAD_REQUEST, "Collar Status should be between 0 to 2!");
        }

        return herdEnum.toString().toLowerCase();
    }

}
