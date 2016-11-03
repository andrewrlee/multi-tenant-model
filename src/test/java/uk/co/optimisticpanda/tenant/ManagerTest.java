package uk.co.optimisticpanda.tenant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import uk.co.optimisticpanda.tenant.Manager;
import uk.co.optimisticpanda.tenant.customer.Customer;
import uk.co.optimisticpanda.tenant.customer.CustomerA;
import uk.co.optimisticpanda.tenant.customer.CustomerB;
import uk.co.optimisticpanda.tenant.customer.CustomerType;

public class ManagerTest {

	private final Manager<Customer> manager = new Manager<>();
	
	@Test
	public void storeAndRetrieve() {
	
		manager.put(CustomerType.A, new CustomerA("1").setDisplayName("Keith"));
		manager.put(CustomerType.B, new CustomerB("3").setAge(12));
		
		Optional<CustomerA> a = manager.find(CustomerType.A, "1");
		
		Optional<CustomerB> bEmpty = manager.find(CustomerType.B, "2");
		
		Optional<CustomerB> b = manager.find(CustomerType.B, "3");
		
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
	
		manager.put(CustomerType.A, new CustomerA("1").setDisplayName("Keith"));
		manager.put(CustomerType.A, new CustomerA("2").setDisplayName("Mark"));
		manager.put(CustomerType.A, new CustomerA("3").setDisplayName("Squirral"));
		
		List<CustomerA> as = manager.getAll(CustomerType.A);
		
		assertThat(as).extracting(CustomerA::getDisplayName).contains("Keith", "Mark", "Squirral");

		List<CustomerB> bs = manager.getAll(CustomerType.B);
		
		assertThat(bs).isEmpty();
	}

	@Test
	public void asType() {
		Customer customer = new CustomerA("1");
		
		assertThat(customer.asType(CustomerType.A)).isPresent();
		assertThat(customer.asType(CustomerType.B)).isEmpty();
	}

	@Test
	public void wontCompile() {
		// ↓↓↓ Won't compile as customers only accept customer type keys ↓↓↓
		// assertThat(customer.asType(AddressType.B)).isEmpty();
		
		// ↓↓↓ Won't compile as trying to use address type key for customer manager ↓↓↓
		//manager.put(AddressType.A, new AAddress("1"));

		// ↓↓↓ Won't compile as trying to pass wrong type of model in for type key ↓↓↓
		//manager.put(CustomerType.A, new BCustomer("1"));
	}

}
