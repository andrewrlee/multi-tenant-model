package uk.co.optimisticpanda.tenant.customer;

import uk.co.optimisticpanda.tenant.Model;

public abstract class Customer extends Model<Customer> {

	protected Customer(String id) {
		super(id);
	}
	
	public Customer(Model<Customer> model) {
		super(model);
	}

	public String getAddress() {
		return (String) get("address");
	}
	
	public void setAddress(String address) {
		put("address", address);
	}

}
