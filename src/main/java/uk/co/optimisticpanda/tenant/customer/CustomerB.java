package uk.co.optimisticpanda.tenant.customer;

import uk.co.optimisticpanda.tenant.Model;

public class CustomerB extends Customer {

	public CustomerB(String id) {
		super(id);
	}
	
	public CustomerB(Model<Customer> model) {
		super(model);
	}

	public CustomerB setAge(int age) {
		put("age", age);
		return this;
	}

	public int getAge() {
		return (int) get("age");
	}
}
