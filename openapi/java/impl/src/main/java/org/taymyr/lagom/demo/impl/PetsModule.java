package org.taymyr.lagom.demo.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import org.taymyr.lagom.demo.api.PetsService;

public class PetsModule extends AbstractModule implements ServiceGuiceSupport {

    @Override
    protected void configure() {
        bindService(PetsService.class, PetsServiceImpl.class);
    }

}
