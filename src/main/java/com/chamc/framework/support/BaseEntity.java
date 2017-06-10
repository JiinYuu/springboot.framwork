package com.chamc.framework.support;

public abstract class BaseEntity {

	public abstract Long getId();
	
	public boolean isNew() {
		return this.getId() == null;
	}
	
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		if(!(other instanceof BaseEntity)) {
			return false;
		}
		BaseEntity otherEntity = (BaseEntity) other;
		return this.getId().equals(otherEntity.getId());
	}
	
	public int hashCode() {
		return this.getId().hashCode();
	}
}
