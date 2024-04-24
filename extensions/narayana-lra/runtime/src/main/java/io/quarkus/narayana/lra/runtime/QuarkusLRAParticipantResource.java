package io.quarkus.narayana.lra.runtime;

import jakarta.ws.rs.Path;

import io.narayana.lra.client.internal.proxy.nonjaxrs.LRAParticipantResource;

@Path(QuarkusLRAParticipantResource.RESOURCE_PATH)
public class QuarkusLRAParticipantResource extends LRAParticipantResource {

    static final String RESOURCE_PATH = "q/lra-participant-proxy";

}
