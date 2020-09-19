package com.encountertavern.demo.controller;

import com.encountertavern.demo.enums.DamageType;
import com.encountertavern.demo.enums.Difficulty;
import com.encountertavern.demo.enums.Language;
import com.encountertavern.demo.enums.Type;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
        name = "Attributes",
        description = "Miscellaneous attributes related to D&D"
)
public class AttributeController {

    @GetMapping("/languages")
    @Operation(description = "Returns all languages that are spoken in the world of D&D")
    public Language[] getLanguages() {
        return Language.values();
    }

    @GetMapping("/difficulties")
    @Operation(description = "Returns the available difficulty level for an encounter")
    public Difficulty[] getDifficulties() {
        return Difficulty.values();
    }

    @GetMapping("/damage-types")
    @Operation(description = "Returns the available difference damage types in the world of D&D")
    public DamageType[] getDamageTypes() {
        return DamageType.values();
    }

    @GetMapping("/types")
    @Operation(description = "Returns all the available types of beings in the world of D&D")
    public Type[] getTypes() {
        return Type.values();
    }

}
