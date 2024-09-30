package syksy24.domain;

import java.util.List;

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
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long departmentid;
	private String name;
	
    
    
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Book> books;
	
	
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	public Long getDepartmentid() {
		return departmentid;
	}
	
	public void setDepartmentid(Long departmentid) {
		this.departmentid = departmentid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getStudents() {
		return books;
	}

	public void setStudents(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Category [departmentid=" + departmentid + ", name=" + name + "]";
	}
}
