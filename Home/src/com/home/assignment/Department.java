package com.home.assignment;

import java.util.HashSet;
import java.util.Set;

public final class Department implements Cloneable, Subject {
	
	private Integer id; //already an object of immutable class, no need to make it final
	private String name; //already an object of immutable class, no need to make it final
	private Set<Student> listOfStudents; //already an list of objects of immutable class, no need to make it final
	
	// a list to maintain the uniqueness of id
	static final private Set<Integer> EXISTINGIDS = new HashSet<>();
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Set<Student> getListOfStudents() throws CloneNotSupportedException {
		Set<Student> tempListOfStudents = new HashSet<>();
		for (Student student : this.listOfStudents) {
			tempListOfStudents.add((Student) student.clone());
		}
		return tempListOfStudents;
	}
	
	public void addStudent(Student student) throws CloneNotSupportedException {
		this.listOfStudents = this.getListOfStudents();
		this.listOfStudents.add(student);
	}

	public Department(Integer id, String name) throws Exception {
		
		if (this.EXISTINGIDS.contains(id))
			throw new Exception("Id already exists");
		
		this.id = id;
		this.EXISTINGIDS.add(this.id);
		
		this.name = name;
		this.listOfStudents = new HashSet<>();
	}
	
	public Department(Integer id, String name, Set<Student> listOfStudents) throws Exception {
		
		if (this.EXISTINGIDS.contains(id))
			throw new Exception("Id already exists");
		
		this.id = id;
		this.EXISTINGIDS.add(this.id);
		
		this.name = name;
		
		this.listOfStudents = this.getListOfStudents();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		this.listOfStudents = this.getListOfStudents();
		return this.clone();
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}

	@Override
	public void sendNotice(String message) {
		System.out.println(this.toString()+" is going to send  student notice:");
		for (Student student : this.listOfStudents) {
			student.getNotice(message);
		}
		System.out.println("Notice sending has been done.");
	}
	
	
}
