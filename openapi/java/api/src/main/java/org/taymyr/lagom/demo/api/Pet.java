package org.taymyr.lagom.demo.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class Pet {
    @Schema(required = true)
    private Long id;
    @Schema(required = true)
    private String name;
    private String tag;
}
