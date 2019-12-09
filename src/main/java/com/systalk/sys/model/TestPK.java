package com.systalk.sys.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the test database table.
 * 
 */
@Embeddable
public class TestPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int categoryId;

	@Column(name="test_value")
	private String testValue;

	public TestPK() {
	}
	public int getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getTestValue() {
		return this.testValue;
	}
	public void setTestValue(String testValue) {
		this.testValue = testValue;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TestPK)) {
			return false;
		}
		TestPK castOther = (TestPK)other;
		return 
			(this.categoryId == castOther.categoryId)
			&& this.testValue.equals(castOther.testValue);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.categoryId;
		hash = hash * prime + this.testValue.hashCode();
		
		return hash;
	}
}