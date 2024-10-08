package syksy24.domain;

import java.util.List;
import java.util.function.IntPredicate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Category {
    public static final String setName = null;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long categoryid;
	private String name;
	
    
    
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Book> books;
    
	
	
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	public Long getCategoryid() {
		return categoryid;
	}
	
	public void setCategory(Long categoryid) {
		this.categoryid = categoryid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", name=" + name + "]";
	}

	public IntPredicate getId() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getId'");
	}

}
