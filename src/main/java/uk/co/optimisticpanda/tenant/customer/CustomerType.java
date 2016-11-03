package uk.co.optimisticpanda.tenant.customer;

import java.util.function.Function;

import uk.co.optimisticpanda.tenant.Tenant;
import uk.co.optimisticpanda.tenant.Type;
import uk.co.optimisticpanda.tenant.Tenant.TenantA;
import uk.co.optimisticpanda.tenant.Tenant.TenantB;

public class CustomerType<TENANT extends Tenant<TENANT>, T extends Customer> extends Type<TENANT, Customer, T> {

	public static CustomerType<TenantA, CustomerA> TENANT_A = new CustomerType<>(Tenant.A, CustomerA.class, CustomerA::new);
	public static CustomerType<TenantB, CustomerB> TENANT_B = new CustomerType<>(Tenant.B, CustomerB.class, CustomerB::new);
	
	private CustomerType(Tenant<TENANT> tenant, Class<T> clazz, Function<Customer, T> factory) {
		super(tenant, clazz, factory);
	}
}
