package io.quarkus.narayana.lra.deployment;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.Operation;
import org.eclipse.microprofile.openapi.models.PathItem;
import org.eclipse.microprofile.openapi.models.Paths;

public class NarayanaLraOpenAPIFilter implements OASFilter {

    boolean openapiIncluded;

    public NarayanaLraOpenAPIFilter(boolean openapiIncluded) {
        this.openapiIncluded = openapiIncluded;
    }

    @Override
    public void filterOpenAPI(OpenAPI openAPI) {

        Paths paths = openAPI.getPaths();

        if (openapiIncluded) {
            // add operation tags
            for (Entry<String, PathItem> pathItemEntry : paths.getPathItems().entrySet()) {
                String path = pathItemEntry.getKey();
                PathItem pathItem = pathItemEntry.getValue();
                if (isLRAPath(path)) {
                    for (Operation operation : pathItem.getOperations().values()) {
                        operation.addTag(getLRATag(path));
                    }
                }
            }

        } else {
            // remove LRA path from OpenAPI
            Set<String> pathsToRemove = new HashSet<>();

            for (String path : paths.getPathItems().keySet()) {
                if (isLRAPath(path)) {
                    pathsToRemove.add(path);
                }
            }

            for (String pathItem : pathsToRemove) {
                paths.removePathItem(pathItem);
            }
        }

    }

    private String getLRATag(String path) {
        if (path.startsWith("/q/lraproxy")) {
            return "Participant Proxy Resource";
        }
        return "LRA Participant Resource";
    }

    private boolean isLRAPath(String path) {
        return path.startsWith("/q/lraproxy") || path.startsWith("/q/lra-participant-proxy");
    }

}
