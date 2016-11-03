package uk.co.optimisticpanda.tenant;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Manager<MODEL extends Model<MODEL>> {

	private final Table<Tenant<?>, String, MODEL> store = HashBasedTable.create();
	
	public <TENANT extends Tenant<TENANT>, T extends MODEL> void put(Type<TENANT, MODEL, T> type, T model) {
		store.put(type.getTenant(), model.getId(), model);
	}
	
	public <TENANT extends Tenant<TENANT>, T extends MODEL> Optional<T> find(Type<TENANT, MODEL, T> type, String id) {
		MODEL model = store.row(type.getTenant()).getOrDefault(id, null);
		return Optional.ofNullable(model).map(type::convert);
	} 
	
	public <TENANT extends Tenant<TENANT>, T extends MODEL> List<T> getAll(Type<TENANT, MODEL, T> type) {
		return store.row(type.getTenant()).values().stream().map(type::convert).collect(toList());
	}
}
