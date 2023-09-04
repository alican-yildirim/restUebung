package org.acme;

import io.vertx.pgclient.PgPool;
import jakarta.inject.Inject;
import org.openapi.quarkus.openapi_yaml.api.LoginApi;
import org.openapi.quarkus.openapi_yaml.model.LoginDaten;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

public class LoginRessource implements LoginApi {

    @Inject
    PgPool client;

    private static boolean ret;

    @Override
    public Boolean loginUser(String email, String passwort) {

        client.query("SELECT * FROM USERS WHERE email='"+ email +"' AND password='"+ passwort +"'").execute(ar -> {

            if (ar.succeeded()) {
                System.out.println("SELECT was successful !");
//              System.out.println("Size:  " + ar.result().size());  // SIZE

                if(ar.result().size()>0){
                    ret = true;
                }else {
                    ret = false;
                }
            } else {
                System.out.println("Failure: " + ar.cause().getMessage());
                ret = false;
            }
        });

        return ret;
    }

    @Override
    public Boolean registerUser(LoginDaten loginDaten) {

//        client.query("INSERT INTO USERS (firstname, lastname, email, password, gender, age) VALUES ('alican', 'yildirim', 'aliicann.yildirim@hotmail.com', 'alican1997', 1, 25)").execute(execute -> {
//                if (execute.succeeded()) {
//                System.out.println("Inserted !");
//                } else {
//                System.out.println("NOT Inserted ! ----- " + execute.toString());
//                }
//        });
        System.out.println(loginDaten);
        return true;
    }

}




//        ---------------------------------------------------------   CHECK CONNECTION
//        client.getConnection().onFailure(e -> {
//            System.out.println(e.getMessage());
//        }).onSuccess(e -> {
//            System.out.println("Es besteht eine Verbindung!");
//        });

//        ---------------------------------------------------------   CREATE TABLE
//    String sql = "CREATE TABLE USERS (ID int generated by default as identity (start with 1 increment by 1) not null," +
//            "firstname varchar(255), lastname varchar(255), email varchar(255), password varchar(255), gender int, age int);";

//        client.query(sql).execute(execute -> {
//            if (execute.succeeded()) {
//                System.out.println("Table created !");
//            } else {
//                System.out.println("Table NOT created !");
//            }
//        });

//        ---------------------------------------------------------   CREATE TABLE MIT ERROR MESSAGE
//      client.query(sql).execute().onSuccess(s -> System.out.println("Table created !")).onFailure(e -> System.out.println("Table NOT created ! Message: " + e.getMessage()));

//        ---------------------------------------------------------   INSERT VALUES
//          client.query("INSERT INTO USERS (firstname, lastname, email, password, gender, age) VALUES ('alican', 'yildirim', 'aliicann.yildirim@hotmail.com', 'alican1997', 1, 25)").execute(execute -> {
//              if (execute.succeeded()) {
//                  System.out.println("Inserted !");
//              } else {
//                  System.out.println("NOT Inserted ! ----- " + execute.toString());
//              }
//          });

//        ---------------------------------------------------------   SELECT SIZE
//        client.query("SELECT * FROM USERS").execute(ar -> {
//
//            if (ar.succeeded()) {
//                System.out.println("SELECT was successful !");
////              System.out.println("Size:  " + ar.result().size());  // SIZE
//            } else {
//                System.out.println("Failure: " + ar.cause().getMessage());
//            }
//        });

//        ---------------------------------------------------------   SELECT VALUES
//        client.query("SELECT * FROM USERS").execute(ar -> {
//                if (ar.succeeded()) {
//                RowSet<Row> rows = ar.result();
//        for (Row row : rows) {
//        System.out.println(row.getInteger(0) + " --- " + row.getString(1) + " --- " + row.getString(2) + " --- " + row.getString(3) + " --- " + row.getString(4) + " --- " + row.getInteger(5) + " --- " + row.getInteger(6));
//        }
//        } else {
//        System.out.println("Failure: " + ar.cause().getMessage());
//        }
//        });