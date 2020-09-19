package com.encountertavern.demo.controller;

import com.encountertavern.demo.dto.EncounterDto;
import com.encountertavern.demo.dto.GenerateEncounterDto;
import com.encountertavern.demo.service.EncounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EncounterController {

    private final EncounterService encounterService;


    @Autowired
    public EncounterController(EncounterService encounterService) {
        this.encounterService = encounterService;
    }

    @RequestMapping(value = "/encounters", method = RequestMethod.GET)
    public List<EncounterDto> getEncounters() {
        return encounterService.getAll();
    }

    @RequestMapping(value = "/encounters", method = RequestMethod.POST)
    public String postEncounter(@RequestBody EncounterDto encounterDto) {
        return encounterService.save(encounterDto);
    }

    @RequestMapping(value = "/encounters/{encounterId}", method = RequestMethod.GET)
    public ResponseEntity<EncounterDto> getEncounter(@PathVariable Long encounterId) {
        return encounterService.getById(encounterId);
    }

    @RequestMapping(value = "/encounters/{encounterId}", method = RequestMethod.PUT)
    public ResponseEntity<EncounterDto> putEncounter(@PathVariable Long encounterId, @RequestBody EncounterDto encounterDto) {
        return encounterService.update(encounterId, encounterDto);
    }

    @RequestMapping(value = "/encounters/generate", method = RequestMethod.POST)
    public long generateEncounter(@RequestBody GenerateEncounterDto encounterDto) {
        return this.encounterService.generateEncounter(encounterDto).getId();
    }

}
