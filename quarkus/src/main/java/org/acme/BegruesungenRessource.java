//package org.acme;
//
//import io.vertx.pgclient.PgPool;
//import jakarta.inject.Inject;
//import org.openapi.quarkus.openapi_yaml.api.BegruesungenApi;
//import org.openapi.quarkus.openapi_yaml.model.Namen;
//
//public class BegruesungenRessource implements BegruesungenApi {
//
//    @Inject
//    PgPool client;
//
//    @Override
//    public String gruesenGet(String vorname, String nachname) {
//        return "Hallo " + vorname + " " + nachname + "!";
//    }
//
//    @Override
//    public String gruesenPost(Namen namen) {
//        return "Hallo " + namen.getVorname() + " " + namen.getNachname() + "!";
//    }
//}
