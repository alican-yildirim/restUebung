package org.acme;

import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import org.openapi.quarkus.openapi_yaml.api.BegruesungenApi;
import org.openapi.quarkus.openapi_yaml.model.Namen;

public class BegruesungenRessource implements BegruesungenApi {

    private static String res;
    @Inject
    PgPool client;

    @PostConstruct
    void config(){
        initDb();
    }

    private void initDb(){
//                .flatMap(m-> client.query("CREATE TABLE hallo (id SERIAL PRIMARY KEY, title TEXT NOT NULL)")).execute();
        this.client.query("DROP TABLE IF EXISTS hallo").execute();
        this.client.query("CREATE TABLE hallo (id SERIAL PRIMARY KEY, title TEXT NOT NULL)");
        this.client.query("INSERT INTO hallo (title) VALUES ('HALLO')").execute();
        this.client.query("INSERT INTO hallo (title) VALUES ('SERVUS')").execute();
    }

    public static String findAll(PgPool PgClient){

        PgClient.query("SELECT id, title FROM hallo ORDER BY title DESC").execute(ar -> {
            if (ar.succeeded()) {
                RowSet<Row> rows = ar.result();
                for (Row row : rows) {
                    res = row.getString(0) + " " + row.getString(1);
                }
            } else {
                res =  "Failure: " + ar.cause().getMessage();
            }
        });

        return res;
    }

    @Override
    public String gruesenGet(String vorname, String nachname) {
//        return "Hallo " + vorname + " " + nachname + "!";
        return findAll(this.client);
    }

    @Override
    public String gruesenPost(Namen namen) {
        return "Hallo " + namen.getVorname() + " " + namen.getNachname() + "!";
    }
}
