 //根据二级菜单获取
 function subMenuClick(subMenuId){
	 //放置到H5全局变量中
	 sessionStorage.subId = subMenuId;
 }
 
$(function() {

		 $.ajax({
				type : "POST",
				url : "/menu/initMenu",
				//data : {asd:"123"},
				dataType:'text',
				success : function(obj) {
					$("#s_menu").html(obj);

					if (sessionStorage.subId == "undefined"){
						//do nothing!
					}else{
						//获取当前页及父页面的中文描述
						var firstNode = $("#"+sessionStorage.subId).parent().parent().find("span").text();
						var secondNode = $("#"+sessionStorage.subId).text();
						$("#firstNode").text(firstNode);
						$("#secondNode").text(secondNode);
						//设置点击后打开状态
						$("#"+sessionStorage.subId).parent().parent().addClass("avtive");
						$("#"+sessionStorage.subId).parent().addClass("menu-open").css("display","block");
						//这里注释掉会有session错乱的风险
						//sessionStorage.subId="undefined";
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("获取菜单数据失败1!");
				}
				
			});

    //退出登录 logout
    $("#signOut").click(function(){

        $("#logoutform").submit();
    });
})
	
