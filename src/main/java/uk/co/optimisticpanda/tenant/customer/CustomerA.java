package uk.co.optimisticpanda.tenant.customer;

import uk.co.optimisticpanda.tenant.Model;

public class CustomerA extends Customer {

	public CustomerA(String id) {
		super(id);
	}
	
	public CustomerA(Model<Customer> model) {
		super(model);
	}

	public CustomerA setDisplayName(String name) {
		put("name", name);
		return this;
	}

	public String getDisplayName() {
		return (String) get("name");
	}
	
}
