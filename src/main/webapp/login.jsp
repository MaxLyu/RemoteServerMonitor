<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/global.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body class="loggedout_body" style="">
<canvas style="position:absolute;z-index:-1;"></canvas>
<div class="loggedout_vcenter">
	<div id="loggedout_container">
		<div id="loggedout_module">
				<form action="login" method="post" role="form" >
					<div><p align="center">Remote Server Monitoring System</p></div>
					<hr class="hr15">
					<div class="login-email">
						<input class="flexwidth100" id="username-input" type="text" placeholder=" User Name" name="username">
					</div>
					<hr class="hr15">
					<div class="login-password">
						<input class="flexwidth100" id="password-input" type="password" placeholder=" Password" name="password">
					</div>

					<hr class="hr15">
					<div class="captcha_container">
						<a href="javascript:;">
							<img src="validateCode" id="validateCode" title="Click to refresh code" onclick="this.src='validateCode?d='+Math.random()"/>
						</a>
							<input class="form-control" id="code-input" type="text" placeholder="Verification Code" name="code">
					</div>

					<hr class="hr15">

							<a  class="login-element"></a>
					<!--
					<input type="checkbox" id="remenber" name="remenber" >Remember Me
					-->
					<hr class="hr15">
					<input type="button" id="submit" name="submit" value="Submit" class="flexwidth100">
					<hr class="hr15">
			</form>

		</div>
	</div>
</div>

<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="./js/login.js" ></script>
<script type="text/javascript" src="./js/background.js" ></script>
</body>
</html>