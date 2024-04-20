package com.pmu.pmu_project.exceptions;

import java.io.Serial;

public class InvalidDataRequestException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidDataRequestException(String msg) {
        super(msg);
    }
}
