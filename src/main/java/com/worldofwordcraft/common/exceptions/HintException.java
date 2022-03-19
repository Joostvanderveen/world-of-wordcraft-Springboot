package com.worldofwordcraft.common.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HintException extends Exception{

    private String message;

    public HintException(String message) {
        this.message = message;

    }
}
