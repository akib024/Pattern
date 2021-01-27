package com.home.assignment;

import java.util.HashSet;
import java.util.Set;

public final class Student implements Cloneable, Observer {
	
	private Integer id; //already an object of immutable class, no need to make it final
	private String name; //already an object of immutable class, no need to make it final
	private Department department; //already an object of immutable class, no need to make it final
	
	// a list to maintain the uniqueness of id
	final static private Set<Integer> EXISTINGIDS = new HashSet<>();
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Department getDepartment() throws Exception {
		return (Department) this.department.clone();
	}

	private Student(StudentBuilder builder) throws Exception {
		
		if (this.EXISTINGIDS.contains(builder.id)) {
			throw new Exception("Id already exists");
		}
		
		this.id = builder.id;
		this.EXISTINGIDS.add(this.id);
		
		this.name = builder.name;
		
		this.department = (Department) builder.department.clone();
		this.department.addStudent(this);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public static class StudentBuilder {
		
		private Integer id; 
		private String name; 
		private Department department;
		
		public StudentBuilder(Integer id, String name, Department department) throws Exception {
			this.id = id;
			this.name = name;
			this.department = department;
		}

		public StudentBuilder setId(Integer id) {
			this.id = id;
			return this;
		}
		
		public StudentBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public StudentBuilder setDepartment(Department department) {
			this.department = department;
			return this;
		}
		
		public Student build() throws Exception{
			return new Student(this);
		}
		
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", department=" + department + "]";
	}

	@Override
	public void getNotice(String message) {
		System.out.println(this.toString()+" has been updated with message: "+message);
	}
}
