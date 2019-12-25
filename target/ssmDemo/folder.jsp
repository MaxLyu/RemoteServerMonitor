<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 2019/11/19
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>folder</title>
    <link rel="stylesheet" type="text/css" href="./css/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./css/easyui/themes/icon.css">
    <link href="css/folder.css" rel="stylesheet" />

</head>
<body>
<div id="canvas" style="width:100%;height:100%"></div>
<div id="mm" class="easyui-menu" data-options="onClick:menuHandler" style="width:120px;">
    <div data-options="name:'Download'">Download</div>
    <div class="menu-sep"></div>
    <div data-options="name:'Delete'">Delete</div>
</div>

<div id="dialog" class="easyui-dialog" title="&nbsp;Upload a File" style="width: 400px; height: 144px;text-align:center"
     data-options="iconCls:'icon-save',resizable:true,modal:false,closed:true"  >
    <form method="post" action="upload" enctype="multipart/form-data">
        <div style="margin:20px">
            <input id="name" name="uploadFile" style="width:250px"/>
        </div>
        <div style="margin:20px">
            <input type="submit" class="easyui-linkbutton" value="&nbsp  Upload  &nbsp"/>
        </div>
    </form>
</div>

<div id="image_back">
    <div id="image_area" class="background"></div>
    <img id="image" >
</div>

<div id="music_back">
    <div id="music_area" class="background"></div>
    <div id="music">
        <audio id="audio" controls="controls" autoplay="autoplay"></audio>
    </div>
</div>

<div id="video_back">
    <div id="video_area" class="background"></div>
    <div id="video">
        <video id="video_player" width="600" height="600" controls="controls"></video>
    </div>
</div>
<script src="./js/jquery-3.3.1.js" type="text/javascript"></script>
<script type="text/javascript" src="./js/easyui/jquery.easyui.min.js"></script>
<script src="./js/folder.js" type="text/javascript"></script>
</body>
</html>
