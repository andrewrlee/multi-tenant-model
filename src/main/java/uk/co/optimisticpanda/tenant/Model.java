package uk.co.optimisticpanda.tenant;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Model<TYPE> {

	private final Map<String, Object> payload = new LinkedHashMap<>();
	
	protected Model(String id) {
		put("id", id);
	}
	
	protected Model(Model<TYPE> model) {
		this.payload.putAll(model.payload);
	}
	
	public <TENANT extends Tenant<TENANT>, E extends TYPE> Optional<E> asType(Type<TENANT, TYPE, E> type) {
		return Optional.ofNullable(this)
				.filter(o -> type.isModelInstance(o))
				.map(type::castToModel);
	}
	
	public String getId() {
		return String.valueOf(payload.get("id"));
	}
	
	protected Object get(String key) {
		return payload.get(key);
	}
	
	protected void put(String key, Object value) {
		payload.put(key, value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model<?> other = (Model<?>) obj;
		if (payload == null) {
			if (other.payload != null)
				return false;
		} else if (!payload.get("id").equals(other.payload.get("id")))
			return false;
		return true;
	}
}
