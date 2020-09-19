package com.encountertavern.demo.controller;

import com.encountertavern.demo.dto.EncounterDto;
import com.encountertavern.demo.dto.GenerateEncounterDto;
import com.encountertavern.demo.service.EncounterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@Tag(name = "Encounters")
public class EncounterController {

    private final EncounterService encounterService;


    @Autowired
    public EncounterController(EncounterService encounterService) {
        this.encounterService = encounterService;
    }

    @GetMapping("/encounters")
    @Operation(description = "Returns all encounters present in the database")
    public List<EncounterDto> getEncounters() {
        return encounterService.getAll();
    }

    @PostMapping("/encounters")
    @Operation(description = "Creates a new encounter",
            responses = {@ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "12",
                                    summary = "Id of the generated encounter"
                            )
                    ))
    })
    public String postEncounter(@RequestBody EncounterDto encounterDto) {
        return encounterService.save(encounterDto);
    }

    @GetMapping("/encounters/{encounterId}")
    @Operation(description = "Returns a specific encounter",
            parameters = @Parameter(
                    name = "encounterId",
                    description = "Id of the encounter that should be returned",
                    in = ParameterIn.PATH,
                    example = "1",
                    required = true
            ))
    public ResponseEntity<EncounterDto> getEncounter(@PathVariable Long encounterId) {
        return encounterService.getById(encounterId);
    }

    @PutMapping("/encounters/{encounterId}")
    @Operation(description = "Updates a specific encounter",
            parameters = @Parameter(
                    name = "encounterId",
                    description = "Id of the encounter that should be updated",
                    in = ParameterIn.PATH,
                    example = "1",
                    required = true
            ))
    public ResponseEntity<EncounterDto> putEncounter(@PathVariable Long encounterId, @RequestBody EncounterDto encounterDto) {
        return encounterService.update(encounterId, encounterDto);
    }

    @PostMapping("/encounters/generate")
    @Operation(description = "Generates an encounter",
                responses = @ApiResponse(
                        responseCode = "200",
                        content = @Content(
                                mediaType = "text/plain",
                                examples = @ExampleObject(
                                        value = "12",
                                        summary = "Id of the generated encounter"
                                )
                        )
                ))
    public long generateEncounter(@RequestBody GenerateEncounterDto encounterDto) {
        return this.encounterService.generateEncounter(encounterDto).getId();
    }

}
