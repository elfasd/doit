package com.asd.common.ztree;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * TODO
 * @author zlp
 * @version TODO
 */
public class ZTreeNodeGrade {
	private BigDecimal id;
	@JsonProperty("pId")
	private Integer pId;
	private String name;
	private Boolean checked;
	private Boolean open;
	private Integer level;
	
	
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public Boolean getChecked() {
		return checked;
	}
	public Boolean getOpen() {
		return open;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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
	public ZTreeNodeGrade(BigDecimal id, Integer pId, String name, Boolean checked, Boolean open,Integer level) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.checked = checked;
		this.open = open;
		this.level = level;
	}
	public ZTreeNodeGrade() {
		super();
	}
	

}
