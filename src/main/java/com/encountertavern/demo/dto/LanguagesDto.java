package com.encountertavern.demo.dto;

import com.encountertavern.demo.enums.Language;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LanguagesDto {

    private List<Language> languages;

}
