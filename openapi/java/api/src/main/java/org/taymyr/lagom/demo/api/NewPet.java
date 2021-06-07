package org.taymyr.lagom.demo.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class NewPet {
    @Schema(required = true)
    String name;
    String tag;
}
