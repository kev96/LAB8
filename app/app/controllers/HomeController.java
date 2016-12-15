package controllers;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import scala.Product;
import views.html.*;

import javax.inject.Inject;

public class HomeController extends Controller {

    public Result index(String name) {
        return ok(index.render("Welcome to the Home page", name));
    }

    public Result about() {
        return ok(about.render());
    }

    public Result products() {
        return ok(products.render());
    }

    public Result addProduct() {

        Form<Product> addProductForm = formFactory.form(Product.class);


        return ok(addProduct.render(addProductForm));
    }

    private FormFactory formFactory;


    @Inject
    public HomeController(FormFactory f) {
        this.formFactory = f;


    }

    public Result addProductSubmit(){
    Form<Product> newProductForm = formFactory.form(Product.class).bindFromRequest();

        if(newProductForm.hasErrors()) {
            return badRequest(addProduct.render(newProductForm));

        }

        Product newProduct = newProductForm.get();
        newProduct.save();


        flash("sucess","product" + newProduct.getName() + "has been created");

        return redirect(controllers.routes.HomeController.products());

    }

    public Result deleteProduct(Long id) {
     Product.find.ref(id).delete();
        flash("success","product has been deleted");
        return redirect(routes.HomeController.Products());

    }







}


