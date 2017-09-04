package com.asd.common.ztree;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * TODO
 * @author zlp
 * @version TODO
 */
public class ZTreeNodeUserGrade {
	private BigDecimal id;
	@JsonProperty("parentId")
	private BigDecimal parentId;
	private String name;
	private Boolean checked;
	private Boolean open;
	private Integer level;
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public BigDecimal getParentId() {
		return parentId;
	}
	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the checked
	 */
	public Boolean isChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	/**
	 * @return the open
	 */
	public Boolean isOpen() {
		return open;
	}
	/**
	 * @param open the open to set
	 */
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public ZTreeNodeUserGrade(BigDecimal id, BigDecimal parentId, String name, Boolean checked, Boolean open,Integer level) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.checked = checked;
		this.open = open;
		this.level = level;
	}
	public ZTreeNodeUserGrade() {
		super();
	}
	

}
