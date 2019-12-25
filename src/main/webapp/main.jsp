<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head id="Head1">
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta http-equiv="x-ua-compatible" content="ie=edge">

<title>SERVER-MONITOR</title>
<meta property="og:title" content="EasyUI">
<meta property="og:description" content="HTML, CSS, JS">

<!-- Meta -->

<link rel="Bookmark" href="./image/monitor.ico" />
<link rel="Shortcut Icon" href="./image/monitor.ico" />
<link href="./css/reset.css" rel="stylesheet" type="text/css" />
<link href="./css/easyui/easyui.css" rel="stylesheet" type="text/css" />
<link href="./css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="./css/layout.css" rel="stylesheet" type="text/css" />


</head>
<body class="easyui-layout vui-easyui" scroll="no">
<noscript>
    <div class="bowerPrompt" class="bowerPrompt">
        <img src="assets/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
    </div>
</noscript>
<!-- 头部 -->
<div data-options="region:'north',split:false,border:false,border:false" class="viewui-navheader">
	<!-- header start -->
	<div class="sys-logo">
		<a href="javascript:;" class="e">logo副标题</a>
	</div>
	<!-- 菜单横栏 -->
	<div class="viewui-user">
        <div class="user-photo">
            <i class="fa fa-user-circle-o"></i>
        </div>
        <h4 class="user-name ellipsis">${sessionScope.user.username }</h4>
        <i class="fa fa-angle-down xiala"></i>

        <div class="viewui-userdrop-down">
            <ul class="user-opt">
                <!--
              <li class="modify-pwd">
                    <a href="javascript:;" id="editpass">
                        <i class="fa fa-edit"></i>
                        <span class="opt-name">修改密码</span>
                    </a>
              </li>
              -->
              <li class="logout">
                    <a href="javascript:;" id="loginOut" onclick="location.href='logout'">
                        <i class="fa fa-power-off"></i>
                        <span class="opt-name">退出</span>
                    </a>
              </li>
            </ul>
        </div>
    </div>
</div>

<!-- 版权 -->
<!-- // 版权 -->
<!-- 左侧菜单 -->
<div data-options="region:'west',hide:true,split:false,border:false" title="&nbsp&nbsp&nbsp&nbspMENU" class="LeftMenu" id="west">
    <div id="nav" class="easyui-accordion" data-options="fit:true,style:'{overflow:hidden}',border:false"></div>
</div>
<!-- // 左侧菜单 -->

<!-- home -->
<div data-options="region:'center'" id="mainPanle" class="home-panel">
	<div id="layout_center_plan" class="easyui-panel"  data-options="fit:true,style:'{overflow:hidden}',closed:false,closable:true,
 	tools:[{ 
 				iconCls:'refresh-panel fa-long-arrow-left ',
 				handler:function(){firstrefresh()} 
 			}]" 
	 style="overflow:hidden">
	</div>
</div>

<div data-options="region:'south'" align="center" style="color:#A9A9A9;font-size:12px">©&nbsp&nbsp Max</div>
<!-- // home -->

<!--修改密码窗口-->
<!--
<div data-options="collapsible:false,minimizable:false,maximizable:false" id="updatePwd" class="easyui-window updatePwd" title="修改密码">
    <div class="row">
       <label for="txtNewPass">新密码：</label>
       <input class="easyui-validatebox txt01" id="txtNewPass" type="Password" name="name" />
    </div>
    <div class="row">
      <label for="txtRePass">确认密码:</label>
      <input class="easyui-validatebox txt01" id="txtRePass" type="Password" name="Password" />
     </div>
    <div data-options="region:'south',border:false" class="pwdbtn">
         <a id="btnEp" class="easyui-linkbutton " href="javascript:;" >确定</a>
         <a id="btnCancel" class="easyui-linkbutton btnDefault" href="javascript:;">取消</a>
     </div>
 </div>
-->

<script src="./js/jquery-3.3.1.js" type="text/javascript"></script>
<script src="./js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src='./js/index.js' type="text/javascript"></script>
<script src='./js/menu.js' type="text/javascript"></script>
<script type="text/javascript">


</script>
</body>
</html>