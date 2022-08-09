package com.herd.test.services.impl;

import com.herd.test.exception.ResourceNotFoundException;
import com.herd.test.exception.RestBusinessException;
import com.herd.test.helper.HerdHelper;
import com.herd.test.model.dto.HerdCreateUpdateDTO;
import com.herd.test.model.dto.HerdDTO;
import com.herd.test.model.entities.Herd;
import com.herd.test.repository.HerdRepository;
import com.herd.test.services.HerdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.herd.test.helper.HerdHelper.herdBuilder;
import static com.herd.test.helper.HerdHelper.herdDTOBuilder;
import static com.herd.test.helper.HerdHelper.herdUpdateBuilder;

@Service
@RequiredArgsConstructor
public class HerdServiceImpl implements HerdService {


    private final HerdRepository herdRepository;


    @Override
    public List<HerdDTO> getAllCows() {
        final List<Herd> herdList = herdRepository.findAll();
        return herdList.stream()
                .map(HerdHelper::herdDTOBuilder)
                .collect(Collectors.toList());
    }

    @Override
    public HerdDTO createNewCow(final HerdCreateUpdateDTO herdCreateUpdateDTO) {
        final Herd herd = herdBuilder(herdCreateUpdateDTO);
        dataEntryValidation(herd);
        herdRepository.save(herd);
        return herdDTOBuilder(herd);
    }

    @Override
    public HerdDTO updateCow(final Integer id, final HerdCreateUpdateDTO herdCreateUpdateDTO) {
        final Herd herd = herdRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Id not found!"));
        final Herd herdUpdated = herdUpdateBuilder(herd, herdCreateUpdateDTO);
        dataEntryValidation(herdBuilder(herdCreateUpdateDTO));
        herdRepository.save(herdUpdated);
        return herdDTOBuilder(herdUpdated);
    }

    @Override
    public void deleteCow(final Integer id) {
        herdRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Id not found!"));
        herdRepository.deleteById(id);
    }

    private void dataEntryValidation(final Herd herd) {
        final Herd herdCowNumber = herdRepository.findCowByNumber(herd.getCowNumber());
        final Herd herdCollarId = herdRepository.findCollarIdByNumber(herd.getCollarId());

        if (Objects.nonNull(herdCollarId)) {
            throw new RestBusinessException(HttpStatus.BAD_REQUEST, "Collar Id already exist in database!");
        }

        if (Objects.nonNull(herdCowNumber)) {
            throw new RestBusinessException(HttpStatus.BAD_REQUEST, "Cow number already exist in database!");
        }
    }

}
