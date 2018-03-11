package io.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    Router router =  Router.router(vertx);
    router.route().handler(StaticHandler.create());

    vertx.createHttpServer()
        .requestHandler(router::accept)
        .listen(8080);
    System.out.println("Server started at http://localhost:8080 ");
  }

}
