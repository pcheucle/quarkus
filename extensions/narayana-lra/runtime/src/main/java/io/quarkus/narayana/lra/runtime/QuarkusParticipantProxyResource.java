package io.quarkus.narayana.lra.runtime;

import jakarta.ws.rs.Path;

import io.narayana.lra.client.internal.proxy.ParticipantProxyResource;

@Path(QuarkusParticipantProxyResource.RESOURCE_PATH)
public class QuarkusParticipantProxyResource extends ParticipantProxyResource {

    static final String RESOURCE_PATH = "q/lraproxy";
}
