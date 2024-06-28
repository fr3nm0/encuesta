package com.fr3nm0.polls_backend.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
public class ValidationErrors {

    private Map<String, String> errors;
    private Date timestamp;
}
