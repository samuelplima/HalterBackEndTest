package com.herd.test.services.impl;

import com.herd.test.exception.ResourceNotFoundException;
import com.herd.test.exception.RestBusinessException;
import com.herd.test.helper.HerdHelper;
import com.herd.test.model.dto.HerdCreateUpdateDTO;
import com.herd.test.model.dto.HerdDTO;
import com.herd.test.model.entities.Farm;
import com.herd.test.model.entities.Herd;
import com.herd.test.repository.FarmRepository;
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

    private final FarmRepository farmRepository;


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
        herdDataEntryValidation(herd);
        herdRepository.save(herd);
        return herdDTOBuilder(herd);
    }

    @Override
    public HerdDTO updateCow(final Integer id, final HerdCreateUpdateDTO herdCreateUpdateDTO) {
        final Herd herd = herdRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Id not found!"));
        final Herd herdUpdated = herdUpdateBuilder(herd, herdCreateUpdateDTO);
        herdDataEntryValidation(herdBuilder(herdCreateUpdateDTO));
        herdRepository.save(herdUpdated);
        return herdDTOBuilder(herdUpdated);
    }

    @Override
    public List<HerdDTO> getAllCowsByFarmId(int id) {
        final List<Herd> herdList = herdRepository.findCowByFarmId(id);

        if(herdList.isEmpty()){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Farm Id not Found!");
        }

        return herdList.stream()
                .map(HerdHelper::herdDTOBuilder)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteCow(final Integer id) {
        herdRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Id not found!"));
        herdRepository.deleteById(id);
    }

    private void herdDataEntryValidation(final Herd herd) {
        final Herd herdCowNumber = herdRepository.findCowByNumber(herd.getCowNumber());
        final Herd herdCollarId = herdRepository.findCollarIdByNumber(herd.getCollarId());
        final Farm farmNameAndId = farmRepository.findFarmByFarmNameAndId(herd.getFarmName(), herd.getFarmId());

        if (Objects.nonNull(herdCollarId)) {
            throw new RestBusinessException(HttpStatus.BAD_REQUEST, "Collar Id already exist in database!");
        }

        if (Objects.nonNull(herdCowNumber)) {
            throw new RestBusinessException(HttpStatus.BAD_REQUEST, "Cow number already exist in database!");
        }

        if (Objects.isNull(farmNameAndId)) {
            throw new RestBusinessException(HttpStatus.BAD_REQUEST, "Farm Does not exist! Type a valid and existent farm");
        }

    }

}
