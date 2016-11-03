# multi-tenant-model
Playing with supporting tenant specific versions of an entity in a typesafe way, when these entities are stored in a schema-less data source

```java
public class ManagerTest {

	private final Manager<Customer> manager = new Manager<>();
	
	@Test
	public void storeAndRetrieve() {
	
		manager.put(CustomerType.TENANT_A, new CustomerA("1").setDisplayName("Keith"));
		manager.put(CustomerType.TENANT_B, new CustomerB("3").setAge(12));
		
		Optional<CustomerA> a = manager.find(CustomerType.TENANT_A, "1");
		
		Optional<CustomerB> bEmpty = manager.find(CustomerType.TENANT_B, "2");
		
		Optional<CustomerB> b = manager.find(CustomerType.TENANT_B, "3");
		
		assertThat(a).isPresent();
		assertThat(bEmpty).isEmpty();
		assertThat(b).isPresent();
		
		assertThat(a.get().getId()).isEqualTo("1");
		assertThat(a.get().getDisplayName()).isEqualTo("Keith");
		
		assertThat(b.get().getId()).isEqualTo("3");
		assertThat(b.get().getAge()).isEqualTo(12);
	}

	@Test
	public void all() {
	
		manager.put(CustomerType.TENANT_A, new CustomerA("1").setDisplayName("Keith"));
		manager.put(CustomerType.TENANT_A, new CustomerA("2").setDisplayName("Mark"));
		manager.put(CustomerType.TENANT_A, new CustomerA("3").setDisplayName("Squirral"));
		
		List<CustomerA> as = manager.getAll(CustomerType.TENANT_A);
		
		assertThat(as).extracting(CustomerA::getDisplayName).contains("Keith", "Mark", "Squirral");

		List<CustomerB> bs = manager.getAll(CustomerType.TENANT_B);
		
		assertThat(bs).isEmpty();
	}

	@Test
	public void asType() {
		Customer customer = new CustomerA("1");
		
		assertThat(customer.asType(CustomerType.TENANT_A)).isPresent();
		assertThat(customer.asType(CustomerType.TENANT_B)).isEmpty();
	}

	@Test
	public void wontCompile() {
		// ↓↓↓ Won't compile as customers only accept customer type keys ↓↓↓
		// assertThat(customer.asType(AddressType.TENANT_B)).isEmpty();
		
		// ↓↓↓ Won't compile as trying to use address type key for customer manager ↓↓↓
		//manager.put(AddressType.TENANT_A, new AAddress("1"));

		// ↓↓↓ Won't compile as trying to pass wrong type of model in for type key ↓↓↓
		//manager.put(CustomerType.TENANT_A, new BCustomer("1"));
	}
}
```
