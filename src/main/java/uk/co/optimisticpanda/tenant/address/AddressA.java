package uk.co.optimisticpanda.tenant.address;

import uk.co.optimisticpanda.tenant.Model;

public class AddressA extends Address {

	public AddressA(String id) {
		super(id);
	}

	public AddressA(Model<Address> model) {
		super(model);
	}

	public AddressA setDisplayName(String name) {
		put("name", name);
		return this;
	}

	public String getDisplayName() {
		return (String) get("name");
	}

}
