<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>  
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <title>后台管理</title>
    <link href="easyui/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui1.css" />
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon1.css" />
    <script type="text/javascript" src="easyui/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="easyui/jQuery.easyui.js"></script>

	<script type="text/javascript" src="easyui/outlook2.js"> </script>

    <script type="text/javascript">

    
	 var _menus = {"menus":[
						{"menuid":"28","icon":"icon-sys","menuname":"选课系统",
							"menus":[{"menuname":"查看学习计划选课","icon":"icon-nav","url":"enroll"},
									{"menuname":"课程表","icon":"icon-nav","url":"plan"},
									{"menuname":"课程管理","icon":"icon-nav","url":"courseManage"},
									{"menuname":"教师管理","icon":"icon-nav","url":"teacherManage"},
									{"menuname":"成绩查询","icon":"icon-nav","url":"grade"},
									{"menuname":"选课情况","icon":"icon-nav","url":"enrollState"},
									
								]
						}
				]};
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '登录',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 200,
                resizable:false
            });
        }
        //关闭登录窗口
        function close() {
            $('#w').window('close');
        }

        

        //修改密码
        function serverLogin() {
            var userId = $('#txtUserId').val();
            var Pass = $('#txtPass').val();
            var type = $('#type').val();

            $.post('person_login.action',{ssn:userId,pwd:Pass,type:type}, function(result) {
                if(result=="true"){
                	msgShow('系统提示', '登录成功');
                	close();
                }
                else{
                	msgShow('系统提示','用户名或密码错误');
                }
            })
            
        }

        $(function() {



           

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

                    if (r) {
                        location.href = '/ajax/loginout.ashx';
                    }
                });

            })
			
			
			
        });
        $(document).ready(function(){ 
      	　　var login = $("#login").val();
      		if(login == "1"){
      			$('#w').window('open');
      		}
      	}); 
		

    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎 您 </span>
        <span style="padding-left:10px; font-size: 16px; "><img src="images/blocks.gif" width="20" height="20" align="absmiddle" /></span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">个人照片管理系统</div>
    </div>
	<%ActionContext a=ActionContext.getContext();
	if(a.getSession().get("userName")==null){
	%>
	<input id="login" type="text" value="1">
    <!--登录窗口-->
    <div id="w" class="easyui-window" title="登录" collapsible="false" minimizable="false" closable="false" modal="true"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 175px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>姓名：</td>
                        <td><input id="txtUserId" type="text" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>密码：</td>
                        <td><input id="txtPass" type="Password" class="txt01" /></td>
                    </tr>
                    <tr>
                    	<td>
                    		<select id="type" name="type">
							  <option value="0">学生</option>
							  <option value="1">教师</option>
							</select>
						</td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" onclick="serverLogin()" >
                    登录</a> 
            </div>
        </div>
    </div>
    <%} %>
    <div region="west" split="true" title="导航菜单" style="width:180px;" id="west">
<div class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
				
			</div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">
				
			<h1>欢迎使用选课系统</h1>

			</div>
		</div>
    </div>
    
   

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>


</body>
</html>