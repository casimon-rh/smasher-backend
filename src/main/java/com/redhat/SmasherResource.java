package com.redhat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

@Path("/")
public class SmasherResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pod> getPods() {
        KubernetesClient k8s = null;
        List<Pod> pods = new ArrayList<>();
        try {
            k8s = new DefaultKubernetesClient();
            pods = k8s.pods().list().getItems().stream().map(x -> new Pod(x.getMetadata().getName(), false))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (k8s != null) {
                k8s.close();
            }
        }
        return pods;
    }
}