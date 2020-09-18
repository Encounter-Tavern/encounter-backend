package com.encountertavern.demo;

import com.encountertavern.demo.enums.DamageType;
import com.encountertavern.demo.enums.Difficulty;
import com.encountertavern.demo.enums.Language;
import com.encountertavern.demo.enums.Type;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttributeController {

    @RequestMapping("/languages")
    public Language[] getLanguages() {
        return Language.values();
    }

    @RequestMapping("/difficulties")
    public Difficulty[] getDifficulties() {
        return Difficulty.values();
    }

    @RequestMapping("/damagetypes")
    public DamageType[] getDamageTypes() {
        return DamageType.values();
    }

    @RequestMapping("/types")
    public Type[] getTypes() {
        return Type.values();
    }

}
