<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" style="width: 100%;height: 100%;">
<head>
  <title>CTFtIme</title>
</head>
<body style="width: 99%;height: 90%;">

<div style="width: 100%;height: 50%;background-color: #343a40;">

  <p style="font-size: 350%;color: white;position: absolute;margin-top: 10%;color:white;font-family: sans-serif;margin-left: 32%;">Welcome! to CTFtIme</p>
</div>

<div style="width: 100%;height: 60%;">

<div style="position: absolute;margin-top: 5%;color:#212529;opacity: 0.8;font-size: 250%;font-family: sans-serif;margin-left: 37%;">Type Your Username </div> 
<form action="<%=request.getContextPath() %>/adduser" method="POST">
<input type="text" name="username" maxlength="15" style="width: 21%;height: 5%;margin-left: 37.5%;position: absolute;margin-top: 8%;font-size: 150%;border-color: #212529;border-style: solid;opacity: 0.4">

<button type="Submit" style="letter-spacing: 1px;
    text-decoration: none;
    -moz-user-select: none;
    border-radius: 0;
    cursor: pointer;
    display: inline-block;
    margin-bottom: 0;
    vertical-align: middle;
    white-space: nowrap;
    font-size: 14px;
    line-height: 20px;
    font-weight: 700;
    padding: 8px 20px;color: #fff;
    background-color: #007bff;
    border-color: #007bff;margin-top: 13%;margin-left: 45%;">Submit</button>
</form>
</div>

<%
Cookie cookies[]=request.getCookies();
System.out.print(cookies);
if(cookies==null){
	
	response.sendRedirect("welcome");
}
for(int i=0;cookies!=null&&i<cookies.length;i++){
    
    if(cookies[i].getName().equals("user")){
    
        response.sendRedirect("homepage");
        
    }
    
}
%>

</body>
</html>