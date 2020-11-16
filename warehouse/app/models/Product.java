package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import akka.event.slf4j.Logger;
import play.data.validation.Constraints;

public class Product {
	
	@Constraints.Required
	public String ean;
	
	@Constraints.Required
	public String name;
	
	public String description;

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
		products.add(new Product("1", "Paperclip 1", "Desc 1"));
		products.add(new Product("2", "Paperclip 2", "Desc 2"));
		products.add(new Product("3", "Paperclip 3", "Desc 3"));
		products.add(new Product("4", "Paperclip 4", "Desc 4"));
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
	public static Product findByName(String name){
		for(Product product: products) {
			if(product.name.toLowerCase().equals(name.toLowerCase())) {
				return product;
			}
		}
		return null;
	}	
	
	public static boolean remove(Product product){
		return products.remove(product);
	}
	
	public void save(){
		products.remove(findByEan(this.ean));
		products.add(this);
	}
}