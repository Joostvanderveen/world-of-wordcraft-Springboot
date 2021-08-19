package com.worldofwordcraft.common.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HintResponse {

    private String hint;

    public HintResponse(String hint) {
        this.hint = hint;
    }
}
