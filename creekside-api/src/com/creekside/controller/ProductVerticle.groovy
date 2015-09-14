package com.creekside.controller

import io.vertx.core.AbstractVerticle
import io.vertx.core.json.JsonObject
import io.vertx.example.util.Runner;
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler

class ProductVerticle extends AbstractVerticle{

  // Convenience method so you can run it in your IDE
  public static void main(String[] args) {
    Runner.runExample(ProductVerticle.class)
  }

  private Map<String, JsonObject> products = new HashMap<>();

  @Override
  public void start() {

    setUpInitialData()

    Router router = Router.router(vertx)

    router.route().handler(BodyHandler.create())
    router.get("/products/:productID").handler(this::handleGetProduct)
    router.put("/products/:productID").handler(this::handleAddProduct)
    router.get("/products").handler(this::handleListProducts)

    vertx.createHttpServer().requestHandler(router::accept).listen(8080)
  }


}
