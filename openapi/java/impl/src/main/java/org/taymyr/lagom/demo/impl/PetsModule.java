package org.taymyr.lagom.demo.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.typesafe.config.Config;
import org.taymyr.lagom.demo.api.PetsService;
import org.taymyr.lagom.javadsl.openapi.OpenAPIRouter;
import play.routing.RoutingDsl;

public class PetsModule extends AbstractModule implements ServiceGuiceSupport {

    @Override
    protected void configure() {
        OpenAPIRouter openAPIRouter = new OpenAPIRouter(
                getProvider(RoutingDsl.class),
                getProvider(PetsServiceImpl.class),
                getProvider(Config.class)
        );
        bindService(PetsService.class, PetsServiceImpl.class, additionalRouter(openAPIRouter));
    }

}
