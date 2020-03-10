package org.taymyr.lagom.demo.impl;

import org.jetbrains.annotations.NotNull;
import com.typesafe.config.Config;
import org.taymyr.lagom.demo.api.EmptyService;
import org.taymyr.lagom.javadsl.openapi.AbstractOpenAPIService;

public class EmptyServiceImpl extends AbstractOpenAPIService implements EmptyService {
    public EmptyServiceImpl(@NotNull Config config) {
        super(config);
    }
}
