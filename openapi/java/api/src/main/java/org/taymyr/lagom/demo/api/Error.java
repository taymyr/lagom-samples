package org.taymyr.lagom.demo.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(requiredProperties = {"code", "message"})
public class Error {
    private Integer code;
    private String message;
}
