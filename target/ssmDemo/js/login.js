var username_input = $("#username-input");
var password_input = $("#password-input");
var submit = $("#submit");
var code_input = $("#code-input");
var module = $(".login-element");
var error = document.createElement("DIV");
error.className = "error_message";

/**
$(function(){
	var cookies = getCookie("userInfo");
	cookies = cookies.substring(0, cookies.length);
	var username = cookies.split("#")[0];
	var password = cookies.split("#")[1];
	// 自动填充用户名和密码
	$("#username-input").val(username);
	$("#password-input").val(password);
})
// 获取Cookie
function getCookie(cname){
	var name = cname + "=";
	var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}
// 记住密码
$("#remenber").click(function(){
	var isRemenber = $("#remenber").is(':checked');
	$("#remenber").val("true");
})
**/

// 密码检验
function password_validate(){
	var password = password_input.val();
	if(password == ""){
		error.innerHTML = "Password is empty";
		module.after(error);
		$(".error_message").show();
	}
	else if(password.length > 15 || password.length < 4){
		error.innerHTML = "Wrong password length";
		module.after(error);
	}else $(".error_message").hide();
}
// 用户名检验
function username_validate(){
	var username = username_input.val();
	var user = {"username": username};
	
	if (username.trim() == "") {
		error.innerHTML = "Username is empty";
		module.after(error);
		username_input.val("").focus();
	}else $(".error_message").hide();
}
// 提交时检验
function submit_validate(){
	var username = username_input.val();
	var password = password_input.val();
	var code = code_input.val();
	//var remenber = $("#remenber").val();
	var user = {"username": username, "password": password, "code":code};
	
	if(username=="" || password==""){
		error.innerHTML = "Invalid username/password";
		module.after(error);
		$(".error_message").show();
	}

	else {
		$(".error_message").hide();
		$.ajax(
				{
					url: "login",
					data:user,
					beforeSend: function(){
						submit.val("Logging in...");
					},
					async: true,
					type: "post",
					dataType: "text",
					success: function(msg){
						if(msg == "correct"){
							window.location.href = "main.jsp";
						}
						if(msg == "wrongCode"){
							error.innerHTML = "Invalid verification code";
							module.after(error);
							$(".error_message").show();
							submit.val("Login");
						}
						if(msg == "false"){
							error.innerHTML = "Invalid username/password";
							module.after(error);
							$(".error_message").show();
							submit.val("Login");
						}
					}
					
				}
		)
	}
	
}

username_input.blur(username_validate);		// 鼠标焦点从用户名移开触发的事件
password_input.blur(password_validate);		// 鼠标移开密码输入框时触发的事件
submit.click(submit_validate);	// 单击提交按钮触发的事件