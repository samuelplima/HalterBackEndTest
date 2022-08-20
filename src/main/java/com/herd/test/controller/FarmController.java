package com.herd.test.controller;

import com.herd.test.model.dto.FarmCreateUpdateDTO;
import com.herd.test.model.dto.FarmDTO;
import com.herd.test.services.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/farm")
@RequiredArgsConstructor
public class FarmController {

    private final FarmService farmService;

    @GetMapping(value = "/getAllFarms")
    public ResponseEntity<List<FarmDTO>> getAllFarms() {
        final List<FarmDTO> farmDTO = farmService.getAllFarms();
        return new ResponseEntity<>(farmDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getFarmById/{id}")
    public ResponseEntity<FarmDTO> getFarmById(@PathVariable Integer id){
        final FarmDTO farmDTO = farmService.findFarmById(id);
        return new ResponseEntity<>(farmDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getFarmByName")
    public ResponseEntity<List<FarmDTO>> getFarmByName(@RequestParam String name) {
        final List<FarmDTO> farmDTO = farmService.findFarmByName(name);
        return new ResponseEntity<>(farmDTO, HttpStatus.OK);
    }


    @PostMapping(value = "/createFarm")
    public ResponseEntity<FarmDTO> createFarm(@RequestBody FarmCreateUpdateDTO farmCreateUpdateDTO) {
        final FarmDTO farmDTO = farmService.createNewFarm(farmCreateUpdateDTO);
        return new ResponseEntity<>(farmDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateFarm/{id}")
    public ResponseEntity<FarmDTO> updateFarm(@PathVariable Integer id, @RequestBody FarmCreateUpdateDTO farmCreateUpdateDTO) {
        final FarmDTO farmDTO = farmService.updateFarm(id, farmCreateUpdateDTO);
        return new ResponseEntity<>(farmDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteFarm/{id}")
    public ResponseEntity<String> deleteFarm(@PathVariable Integer id) {
        final String msg = "Farm Id " + id + " deleted!";
        farmService.deleteFarm(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
