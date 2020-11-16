package controllers;

import java.util.List;

import models.Product;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.products.details;

public class Products extends Controller {
	
	public static final Form<Product> productForm = Form.form(Product.class);
	
	public static Result list() {
		List<Product> products = Product.findAll();
		return ok(views.html.products.list.render(products));
	}
	
	public static Result newProduct() {
		return ok(views.html.products.details.render(productForm));
	}
	
	public static Result details(String ean) {
		final Product product = Product.findByEan(ean);
		if(product == null) {
			return notFound(String.format("Product with EAN value: \"%s\", does not exist.", ean));
		}
		Form<Product> filledForm = productForm.fill(product);
		return ok(details.render(filledForm));
	}
	
	public static Result delete(String ean) {
		final Product product = Product.findByEan(ean);
		if(product == null) {
			return notFound(String.format("Product with EAN value: \"%s\" does not exist, therefore cannot be deleted.", ean));
		}
		Product.remove(product);
		System.out.println(String.format("Product with EAN value: \"%s\" has been deleted.", ean));
		return redirect(routes.Products.list());
	}
	
	public static Result save() { 
		Form<Product> boundForm = productForm.bindFromRequest();
		if(boundForm.hasErrors()) {
			flash("error", "please correct the form below");
			return badRequest(details.render(boundForm));
		}
		
		Product product = boundForm.get();
		product.save();
		flash("success", String.format("Successfully added product %s", product));
		
		return redirect(routes.Products.list());
	}
}
