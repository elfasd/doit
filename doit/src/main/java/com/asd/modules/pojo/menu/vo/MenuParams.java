package com.asd.modules.pojo.menu.vo;

import java.util.ArrayList;
import java.util.List;

public class MenuParams {

	private String href;
	
	private String icon;
	
	
	private String name;

	private String active ="";
	
	private String menu_id="";
	
	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
	public static void main(String args[]){
		
		String prefix = "<li class='treeview'>";
		String suffix = "</li>";
		
		List<MenuParams> lm1 = new ArrayList<MenuParams>();
		MenuParams m1 = new MenuParams();
		m1.setHref("#");
		m1.setIcon("fa fa-files-o");
		m1.setName("�˵�ҵ�����");
		m1.setActive("active");
		m1.setMenu_id("1");
		MenuParams m11 = new MenuParams();
		m11.setName("��Լҵ���˵�����");
		m11.setIcon("fa fa-circle-o");
		m11.setHref("/reas/pages/treatyfz/treatyfzQuery.jsp");
		MenuParams m12 = new MenuParams();
		m12.setName("�ٷ�ҵ���˵�����");
		m12.setIcon("fa fa-circle-o");
		m12.setHref("/reas/pages/facfz/facfzQuery.jsp");
		m12.setActive("active");
		MenuParams m13 = new MenuParams();
		m13.setName("�ٷ�ҵ�����ݹ���");
		m13.setIcon("fa fa-circle-o");
		m13.setHref("#");
		lm1.add(m11);
		lm1.add(m12);
		lm1.add(m13);
		
		MenuFirst f1 = new MenuFirst(m1);
		MenuSecond f11 = new MenuSecond(lm1);
		f1.setSecondMenu(f11);
		
		
		List<MenuParams> lm2 = new ArrayList<MenuParams>();
		MenuParams m2 = new MenuParams();
		m2.setHref("#");
		m2.setIcon("fa fa-files-o");
		m2.setName("��������");
		MenuParams m21 = new MenuParams();
		m21.setName("�˵�����ҵ����");
		m21.setIcon("fa fa-circle-o");
		m21.setHref("#");
		lm2.add(m21);
		
		MenuFirst f2= new MenuFirst(m2);
		MenuSecond f21 = new MenuSecond(lm2);
		f2.setSecondMenu(f21);

		
		String menu2 = prefix+f2.toString()+f21.toString()+suffix;

		
		List<MenuParams> lm3 = new ArrayList<MenuParams>();
		MenuParams m3 = new MenuParams();
		m3.setHref("#");
		m3.setIcon("fa fa-files-o");
		m3.setName("�ٱ����˹���");
		MenuParams m31 = new MenuParams();
		m31.setName("�ٱ����˹���");
		m31.setIcon("fa fa-circle-o");
		m31.setHref("#");
		lm3.add(m31);
		
		MenuFirst f3= new MenuFirst(m3);
		MenuSecond f31 = new MenuSecond(lm3);
		f3.setSecondMenu(f31);

		
		
		List<MenuParams> lm4 = new ArrayList<MenuParams>();
		MenuParams m4 = new MenuParams();
		m4.setHref("#");
		m4.setIcon("fa fa-files-o");
		m4.setName("ϵͳ����");
		MenuParams m41 = new MenuParams();
		m41.setName("��Ϣ����");
		m41.setIcon("fa fa-circle-o");
		m41.setHref("#");
		lm4.add(m41);
		
		MenuFirst f4= new MenuFirst(m4);
		MenuSecond f41 = new MenuSecond(lm4);
		
		f4.setSecondMenu(f41);

		String back = f1.toString() + f2.toString() + f3.toString() + f4.toString();
		
		
		System.out.println(back);
		
	}
	
	
	
}
