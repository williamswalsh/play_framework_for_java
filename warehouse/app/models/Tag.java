package models;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import play.data.validation.Constraints;

public class Tag {
	public Long id;
	
	@Constraints.Required
	public String name;
	
	public List<Product> products;

	private static List<Tag> tags = new LinkedList<Tag>();
	
	static {
		tags.add(new Tag(1L, "Light weight",Product.findByName("Paperclip 1")));
		tags.add(new Tag(2L, "Metal",Product.findByName("Paperclip 2")));
		tags.add(new Tag(3L, "Plastic",Product.findByName("Paperclip 3")));
	}
	
	public Tag() {}
	
	public Tag(Long id, String name, Collection<Product> products) {
		this.id = id;
		this.name = name;
		this.products = new LinkedList<Product>(products);
	}
	
	public static Tag findById(Long id) {
		for(Tag tag: tags) {
			if(tag.id == id) {
				return tag;
			}
		}
		return null;
	}
}
