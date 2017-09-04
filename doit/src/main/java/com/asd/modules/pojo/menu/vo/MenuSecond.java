package com.asd.modules.pojo.menu.vo;

import java.util.List;

public class MenuSecond {

	public MenuSecond(){
		
	}
	public MenuSecond(List<MenuParams> params){
		this.params = params;
	}
	
	private List<MenuParams> params;
	
	
	public List<MenuParams> getParams() {
		return params;
	}
	public void setParams(List<MenuParams> params) {
		this.params = params;
	}
	
	
	public String toString(){
		
		
		String ulprefix = "<ul class='treeview-menu'>";
		String ulsuffix = "</ul>";
		String sub = "";
		
		
		for(MenuParams p : params){
			String tmp = "<li class='%s' id='%s'><a href='%s'  onclick=\"subMenuClick('%s')\" > <i class='%s'/>%s</a></li>";
			sub+=String.format(tmp, p.getActive(),p.getMenu_id(),p.getHref(),p.getMenu_id(),p.getIcon(),p.getName());
		}	
		
		
		return ulprefix+sub+ulsuffix;
		
	}
	
	
	
}
