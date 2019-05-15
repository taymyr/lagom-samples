package org.taymyr.lagom.demo.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.typesafe.config.Config;
import org.taymyr.lagom.demo.api.NewPet;
import org.taymyr.lagom.demo.api.Pet;
import org.taymyr.lagom.demo.api.PetsService;
import org.taymyr.lagom.javadsl.openapi.AbstractOpenAPIService;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

/**
 * Implementation of the PetsService.
 */
public class PetsServiceImpl extends AbstractOpenAPIService implements PetsService {

    @Inject
    public PetsServiceImpl(Config config) {
        super(config);
    }

    @Override
    public ServiceCall<NotUsed, List<Pet>> find(List<String> tags, Optional<Integer> limit) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ServiceCall<NewPet, Pet> create() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ServiceCall<NotUsed, Pet> findBy(Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ServiceCall<NotUsed, NotUsed> delete(Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }

}
