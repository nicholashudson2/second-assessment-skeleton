package com.cooksys.twittr.entity;

import java.util.Set;

import javax.persistence.Embeddable;

@Embeddable
public class Context {
	
	private Integer id;
	
	private Tweet target;
	
	private Set<Tweet> before;
	
	private Set<Tweet> after;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the target
	 */
	public Tweet getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Tweet target) {
		this.target = target;
	}

	/**
	 * @return the before
	 */
	public Set<Tweet> getBefore() {
		return before;
	}

	/**
	 * @param before the before to set
	 */
	public void setBefore(Set<Tweet> before) {
		this.before = before;
	}

	/**
	 * @return the after
	 */
	public Set<Tweet> getAfter() {
		return after;
	}

	/**
	 * @param after the after to set
	 */
	public void setAfter(Set<Tweet> after) {
		this.after = after;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Context other = (Context) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
