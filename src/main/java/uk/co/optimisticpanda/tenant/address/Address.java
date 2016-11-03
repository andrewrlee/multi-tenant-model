package uk.co.optimisticpanda.tenant.address;

import uk.co.optimisticpanda.tenant.Model;

public abstract class Address extends Model<Address> {

	protected Address(String id) {
		super(id);
	}
	
	public Address(Model<Address> model) {
		super(model);
	}

	public String getAddress() {
		return (String) get("address");
	}
	
	public void setAddress(String address) {
		put("address", address);
	}

}
