package com.asd.common.ztree;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TODO
 * @author zlp
 * @version TODO
 */
public class ZTreeNodeTask {
	private String id;
	@JsonProperty("pId")
	private String pId;
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
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the pId
	 */
	public String getPId() {
		return pId;
	}
	/**
	 * @param id the pId to set
	 */
	public void setPId(String id) {
		pId = id;
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
	public ZTreeNodeTask(String id, String pId, String name, Boolean checked, Boolean open,Integer level) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.checked = checked;
		this.open = open;
		this.level = level;
	}
	public ZTreeNodeTask() {
		super();
	}
	

}
