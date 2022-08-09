package com.herd.test.controller;

import com.herd.test.model.dto.HerdCreateUpdateDTO;
import com.herd.test.model.dto.HerdDTO;
import com.herd.test.services.HerdService;
import lombok.RequiredArgsConstructor;
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
    public List<HerdDTO> getAllCows() {
        return herdService.getAllCows();
    }

    @PostMapping(value = "/createCow")
    public HerdDTO createCow(@RequestBody HerdCreateUpdateDTO herdCreateUpdateDTO) {
        return herdService.createNewCow(herdCreateUpdateDTO);
    }

    @PutMapping(value = "/updateCow/{id}")
    public HerdDTO updateCow(@PathVariable Integer id, @RequestBody HerdCreateUpdateDTO herdCreateUpdateDTO) {
        return herdService.updateCow(id, herdCreateUpdateDTO);
    }

    @DeleteMapping(value = "/deleteCow/{id}")
    public String deleteCow(@PathVariable Integer id) {
        herdService.deleteCow(id);
        return "Cow Id " + id + " deleted!";
    }


}
