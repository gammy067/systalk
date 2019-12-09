package com.systalk.sys.web.form.component.combo;

/**
 * 下拉選單 項目.
 */
public class ComboItem {

	/** The label. */
	private String label = "";

	/** The value. */
	private String value = "";

	/**
	 * Instantiates a new combo item.
	 *
	 * @param sLabel the s label
	 * @param sValue the s value
	 */
	public ComboItem(String sLabel, String sValue) {
		this.label = sLabel;
		this.value = sValue;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
