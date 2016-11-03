package uk.co.optimisticpanda.tenant.address;

import java.util.function.Function;

import uk.co.optimisticpanda.tenant.Tenant;
import uk.co.optimisticpanda.tenant.Type;
import uk.co.optimisticpanda.tenant.Tenant.TenantA;
import uk.co.optimisticpanda.tenant.Tenant.TenantB;

public class AddressType<TENANT extends Tenant<TENANT>, T extends Address> extends Type<TENANT, Address, T> {

	public static AddressType<TenantA, AddressA> TENANT_A = new AddressType<>(Tenant.A, AddressA.class, AddressA::new);
	public static AddressType<TenantB, AddressB> TENANT_B = new AddressType<>(Tenant.B, AddressB.class, AddressB::new);
	
	private AddressType(Tenant<TENANT> tenant, Class<T> clazz, Function<Address, T> factory) {
		super(tenant, clazz, factory);
	}
}
