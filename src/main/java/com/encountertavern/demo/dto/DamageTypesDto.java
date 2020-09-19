package com.encountertavern.demo.dto;

import com.encountertavern.demo.enums.DamageType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DamageTypesDto {

    private List<DamageType> damageTypes;

}
