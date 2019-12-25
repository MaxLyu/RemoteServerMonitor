<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 2019/11/18
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./css/easyui/themes/icon.css">
    <script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="./js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="./js/fileTree.js"></script>
    <title>fileDev</title>
</head>
<body>
    <ul id="tt" class="easyui-tree" data-options="url:'fileTree',method:'post',animate:true,lines:false"></ul>
</body>
</html>
