package edu.mum.cs544.l01.model;

public class Person {
/*
    Setter Injection
    Setter-based DI is realized by calling setter methods on the user’s beans
    after invoking a no-argument constructor
    or no-argument static factory method to instantiate their bean.*/
	private int id;
        private String firstname;
        private String lastname;

        public Person() {
        }

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
    
}
