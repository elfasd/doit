package com.asd.modules.pojo.menu.vo;

public class MenuFirst {
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public MenuFirst(){
		
	}
	
	public  MenuFirst(MenuParams params){
		this.params = params;
	}

	
	
	private MenuParams params; 

	private MenuSecond secondMenu;
	
	
	
	public MenuSecond getSecondMenu() {
		return secondMenu;
	}

	public void setSecondMenu(MenuSecond secondMenu) {
		this.secondMenu = secondMenu;
	}

	public String getFirstLevel() {
		return firstLevel;
	}

	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}
	


	private String firstLevel = "<li class='treeview %s' id='%s'><a href='%s'><i class='%s'/><span>%s</span><i class='fa fa-angle-left pull-right'/></a>%s</ul></li>";

	
	public String toString(){
		
		 return String.format(firstLevel,params.getActive(),params.getMenu_id(), params.getHref(),params.getIcon(),params.getName(),secondMenu.toString());

	}
	
	
	
}
