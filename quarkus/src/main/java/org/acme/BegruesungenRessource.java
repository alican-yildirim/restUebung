package org.acme;

import io.vertx.core.Future;
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
        initdb();
    }

    private void initdb(){

//        this.client.query("DROP TABLE IF EXISTS hallo").execute().flatMap(m-> client.query("CREATE TABLE hallo (id SERIAL PRIMARY KEY, title TEXT NOT NULL)")).execute();;



//        this.client.query("CREATE TABLE hallo (id SERIAL PRIMARY KEY, title TEXT NOT NULL)");
//        this.client.query("INSERT INTO hallo (title) VALUES ('HALLO')").execute();
//        this.client.query("INSERT INTO hallo (title) VALUES ('SERVUS')").execute();
    }

    public String findAll(PgPool PgClient){

        PgClient.query("SELECT id, title FROM hallo").execute(ar -> {
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
        client.getConnection().compose(conn -> {
            System.out.println("Got a connection from the pool");

        conn.query("CREATE TABLE hallo (id SERIAL PRIMARY KEY, title TEXT NOT NULL)").execute();
        conn.query("INSERT INTO hallo (title) VALUES ('HALLO')").execute();

            // All operations execute on the same connection
            return conn
                    .query("SELECT * FROM hallo")
                    .execute()
                    .onComplete(ar -> {
                        // Release the connection to the pool
                        conn.close();
                    });
        }).onComplete(ar -> {
            if (ar.succeeded()) {

                System.out.println("Done");
            } else {
                System.out.println("Something went wrong " + ar.cause().getMessage());
            }
        });

//        return this.findAll(this.client);
        return "";
    }

    @Override
    public String gruesenPost(Namen namen) {
        return "Hallo " + namen.getVorname() + " " + namen.getNachname() + "!";
    }
}
