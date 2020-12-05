package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import akka.event.slf4j.Logger;
import play.data.validation.Constraints;

public class Product {
	
	@Constraints.Required
	public String ean;
	
	@Constraints.Required
	public String name;
	
	public String description;

	public List<Tag> tags = new LinkedList<Tag>();
	
	public Product() {
	}

	public Product(String ean, String name, String description) {
		this.ean = ean;
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("Product [ean=%s, name=%s]", ean, name);
	}

	private static List<Product> products;

	static {
		products = new ArrayList<Product>();
		products.add(new Product("0001", "Paperclip 1", "Desc 1"));
		products.add(new Product("0002", "Paperclip 2", "Desc 2"));
		products.add(new Product("0003", "Paperclip 3", "Desc 3"));
		products.add(new Product("0004", "Paperclip 4", "Desc 4"));
		products.add(new Product("0005", "Paperclip 4", "Desc 4"));
		products.add(new Product("0006", "Paperclip 4", "Desc 4"));
		products.add(new Product("0007", "Paperclip 4", "Desc 4"));
		products.add(new Product("0008", "Paperclip 4", "Desc 4"));
		products.add(new Product("0009", "Paperclip 4", "Desc 4"));
		products.add(new Product("0010", "Paperclip 4", "Desc 4"));
		products.add(new Product("0011", "Paperclip 4", "Desc 4"));
		products.add(new Product("0012", "Paperclip 4", "Desc 4"));
		products.add(new Product("0013", "Paperclip 4", "Desc 4"));
		products.add(new Product("0014", "Paperclip 4", "Desc 4"));
		products.add(new Product("0015", "Paperclip 4", "Desc 4"));
		products.add(new Product("0016", "Paperclip 4", "Desc 4"));
		products.add(new Product("0017", "Paperclip 4", "Desc 4"));
		products.add(new Product("0018", "Paperclip 4", "Desc 4"));
		products.add(new Product("0019", "Paperclip 4", "Desc 4"));
		products.add(new Product("0020", "Paperclip 4", "Desc 4"));
		products.add(new Product("0021", "Paperclip 4", "Desc 4"));
		products.add(new Product("0022", "Paperclip 4", "Desc 4"));
		products.add(new Product("0023", "Paperclip 4", "Desc 4"));
		products.add(new Product("0024", "Paperclip 4", "Desc 4"));
		products.add(new Product("0025", "Paperclip 4", "Desc 4"));
		products.add(new Product("0026", "Paperclip 4", "Desc 4"));
	}
	
	public static List<Product> findAll(){
		return new ArrayList<Product>(products);
	}

	public static Product findByEan(String ean){
		for(Product product: products) {
			if(product.ean.equals(ean)) {
				return product;
			}
		}
		return null;
	}

	
	// TODO - Change to return a list of products
	public static List<Product> findByName(String name){
		final List<Product> results = new ArrayList<Product>();
		for(Product product: products) {
			if(product.name.toLowerCase().equals(name.toLowerCase())) {
				results.add(product);
			}
		}
		return results;
	}	
	
	public static boolean remove(Product product){
		return products.remove(product);
	}
	
	public void save(){
		products.remove(findByEan(this.ean));
		products.add(this);
	}
}