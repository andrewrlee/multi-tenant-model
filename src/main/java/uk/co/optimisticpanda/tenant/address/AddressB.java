package uk.co.optimisticpanda.tenant.address;

import uk.co.optimisticpanda.tenant.Model;

public class AddressB extends Address {

	protected AddressB(String id) {
		super(id);
	}
	
	public AddressB(Model<Address> model) {
		super(model);
	}

	public AddressB setAge(int age) {
		put("age", age);
		return this;
	}

	public int getAge() {
		return (int) get("age");
	}
}
