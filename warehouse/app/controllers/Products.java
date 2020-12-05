package controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import models.Product;
import models.Tag;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

@With(CatchAction.class)
public class Products extends Controller {
	
//	Creating a form object
	public static final Form<Product> productForm = Form.form(Product.class);
	
	public static Result index() {
		return redirect(routes.Products.list(0));
	}
	
	public static Result list(Integer page) {
		List<Product> products = Product.findAll();
		
		// Switches return value depending on Accept header value: text/html / text/plain / <BLANK>
		if(request().accepts("text/html")) {
			  return ok(views.html.products.list.render(products));
		}
		return ok(StringUtils.join(products, "\n"));
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
		return ok(views.html.products.details.render(filledForm));
	}
	
	public static Result delete(String ean) {
		final Product product = Product.findByEan(ean);
		if(product == null) {
			return notFound(String.format("Product with EAN value: \"%s\" does not exist, therefore cannot be deleted.", ean));
		}
		Product.remove(product);
		System.out.println(String.format("Product with EAN value: \"%s\" has been deleted.", ean));
		return redirect(routes.Products.list(1));
	}
	
	public static Result save() { 
		Form<Product> boundForm = productForm.bindFromRequest();
		if(boundForm.hasErrors()) {
			flash("error", "please correct the form below");
			return badRequest(views.html.products.details.render(boundForm));
		}
		
		Product product = boundForm.get();
		
		List<Tag> tags = new ArrayList<Tag>();
		for(Tag tag: product.tags) {
			if(tag.id != null) {
				tags.add(Tag.findById(tag.id));
			}
		}
		product.tags = tags;
		product.save();
		flash("success", String.format("Successfully added product %s", product));
		
		return redirect(routes.Products.list(1));
	}
}
