package io.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    Router router =  Router.router(vertx);
    StaticHandler serve =  StaticHandler.create();
    ThymeleafTemplateEngine engine = ThymeleafTemplateEngine.create();

    router.route("/assets/*").handler(serve);
    router.get("/").handler(ctx -> {
      // we define a hardcoded title for our application
      ctx.put("welcome", "Hi there!");
      // and now delegate to the engine to render it.
      engine.render(ctx,"templates/", "index.html", res -> {
        if (res.succeeded()) {
          ctx.response().end(res.result());
        } else {
          ctx.fail(res.cause());
        }
      });
    });

    vertx.createHttpServer()
        .requestHandler(router::accept)
        .listen(8080);
    System.setProperty("vertx.disableFileCaching", "true");
    System.out.println("Server started at http://localhost:8080 ");
  }

}
