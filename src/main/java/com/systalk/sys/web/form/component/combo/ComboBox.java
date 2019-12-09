package com.systalk.sys.web.form.component.combo;

import java.util.ArrayList;
import java.util.List;

/**
 * 下拉選單.
 * */
public class ComboBox {
	
    /** The options. */
    private List<ComboItem> options = new ArrayList<ComboItem>();

    /** The disable. */
    private Boolean disable = false;
    
    /** The selected. */
    private Boolean selected = false;
    
    /** The Constant DEFAULT_LABEL. */
    private static final String DEFAULT_LABEL = "-----請選擇-----";
    
    /** The Constant DEFAULT_VALUE. */
    private static final String DEFAULT_VALUE = "none";

    /**
     * 新增預設的ComboItem.
     */
    public void addDefaultComboItem() {
        this.add(DEFAULT_LABEL, DEFAULT_VALUE);
    }
    
    /**
     * Adds the.
     *
     * @param sLabel            the s label.
     * @param sValue            the s value.
     */
    public void add(String sLabel, String sValue) {
        ComboItem item = new ComboItem(sLabel, sValue);
        add(item);
    }

    /**
     * Adds the.
     * @param item
     *            the item.
     */
    public void add(ComboItem item) {
        options.add(item);
    }

	/**
	 * Gets the options.
	 *
	 * @return the options
	 */
	public List<ComboItem> getOptions() {
		return options;
	}

	/**
	 * Sets the options.
	 *
	 * @param options the new options
	 */
	public void setOptions(List<ComboItem> options) {
		this.options = options;
	}

	/**
	 * Gets the disable.
	 *
	 * @return the disable
	 */
	public Boolean getDisable() {
		return disable;
	}

	/**
	 * Sets the disable.
	 *
	 * @param disable the new disable
	 */
	public void setDisable(Boolean disable) {
		this.disable = disable;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
