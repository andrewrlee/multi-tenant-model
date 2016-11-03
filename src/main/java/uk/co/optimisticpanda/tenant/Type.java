package uk.co.optimisticpanda.tenant;

import java.util.function.Function;

public abstract class Type<TENANT extends Tenant<TENANT>, S, T extends S> {

	private final Tenant<TENANT> tenant;
	private final Function<S, T> modelFactory;
	private final Class<T> modelClass;
	
	protected Type(Tenant<TENANT> tenant, Class<T> clazz, Function<S, T> factory) {
		this.tenant = tenant;
		this.modelClass = clazz;
		this.modelFactory = factory;
	}

	public Tenant<TENANT> getTenant() {
		return tenant;
	}
	
	public boolean isModelInstance(Object o) {
		return modelClass.isInstance(o);
	}
	
	public T castToModel(Model<S> model) {
		return modelClass.cast(model);
	}
	
	public T convert(S model) {
		return modelFactory.apply(model);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tenant == null) ? 0 : tenant.hashCode());
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
		Type<?, ?, ?> other = (Type<?, ?, ?>) obj;
		if (tenant == null) {
			if (other.tenant != null)
				return false;
		} else if (!tenant.equals(other.tenant))
			return false;
		return true;
	}

}
