package uk.co.optimisticpanda.tenant;

public class Tenant<THIS extends Tenant<THIS>> {

	public static Tenant<TenantA> A = new TenantA();
	public static Tenant<TenantB> B = new TenantB();
	
	private String name;

	private Tenant(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static class TenantA extends Tenant<TenantA> {
		public TenantA() {
			super("tenant-a");
		}
	}
	public static class TenantB extends Tenant<TenantB> {
		public TenantB() {
			super("tenant-b");
		}
	}
}
