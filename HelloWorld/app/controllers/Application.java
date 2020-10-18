package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        // return ok(index.render("Your new application is ready."));
        return ok("Hello World William! This is HAL. :-P");
    }

//    http://localhost:9000/personalisedHello?name=joey%20hilliard -> Hello World joey hilliard!
//    http://localhost:9000/personalisedHello?name=william -> Hello World william!
//    public static Result personalisedHello(String name) {
//        return ok("Hello World " + name + "!");
//    }

//    http://localhost:9000/hello?name=william%20w
    public static Result personalisedHello(String name) {
        return ok(views.html.hello.render(name));
    }
}
