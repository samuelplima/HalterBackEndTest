package com.herd.test.controller;

import com.herd.test.model.dto.HerdCreateUpdateDTO;
import com.herd.test.model.dto.HerdDTO;
import com.herd.test.services.HerdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/herd")
@RequiredArgsConstructor
public class HerdController {
    private final HerdService herdService;

    @GetMapping(value = "/getAllCows")
    public ResponseEntity<List<HerdDTO>> getAllCows() {
        final List<HerdDTO> herdDTO = herdService.getAllCows();
        return new ResponseEntity<>(herdDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/createCow")
    public ResponseEntity<HerdDTO> createCow(@RequestBody HerdCreateUpdateDTO herdCreateUpdateDTO) {
        final HerdDTO herdDTO = herdService.createNewCow(herdCreateUpdateDTO);
        return new ResponseEntity<>(herdDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateCow/{id}")
    public ResponseEntity<HerdDTO> updateCow(@PathVariable Integer id, @RequestBody HerdCreateUpdateDTO herdCreateUpdateDTO) {
        final HerdDTO herdDTO = herdService.updateCow(id, herdCreateUpdateDTO);
        return new ResponseEntity<>(herdDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteCow/{id}")
    public ResponseEntity<String> deleteCow(@PathVariable Integer id) {
        final String msg = "Cow Id " + id + " deleted!";
        herdService.deleteCow(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


}
