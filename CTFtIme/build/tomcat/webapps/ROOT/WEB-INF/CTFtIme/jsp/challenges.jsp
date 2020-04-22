<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="EN">
<head><title>CTFtIme</title></head>
<body>
<%
String username="";
int count=0;
Cookie cookies[]=request.getCookies();
if(cookies==null){
	response.sendRedirect("welcome");
	count+=1;
}

for(int i=0;cookies!=null&&i<cookies.length;i++){
    
    if(cookies[i].getName().equals("user")){
    
        username=cookies[i].getValue();
        count=count+1;
        
    }
    
}

if(count==0){
    response.sendRedirect("welcome");
}
%>
<center><p style="font-size:30px;">GO TO A CHALLENGE AND DO.</p></center>
</body>
</html>
