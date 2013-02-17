package controllers;

import java.math.*;

import models.Task;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    static Form<Task> taskForm = Form.form(Task.class);

    public static Result index() {
        return ok("value");
    }

    public static Result tasks() {
        return ok(views.html.index.render(Task.all(),  taskForm));
    }

    public static Result newTasks() {
        Form<Task> filledForm = taskForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(views.html.index.render(Task.all(), filledForm));
        } else {
            Task.create(filledForm.get());
            return redirect(routes.Application.tasks());
        }
    }

    public static Result deleteTask(Long id) {
        Task.delete(id);
        return redirect(routes.Application.tasks());
    }

    public static Result factorial() {
        BigInteger value = models.Factor.factorial(10);
        return ok(value.toString());
    }

}
