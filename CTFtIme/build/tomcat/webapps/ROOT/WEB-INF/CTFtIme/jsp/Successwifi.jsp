<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CTFtIme</title>
</head>
<body>
<%String username="";
Cookie cookies[]=request.getCookies();
String user="";
int count2=0;
int count=0;
if(cookies==null){
	response.sendRedirect("welcome");
	count2+=1;
}


for(int i=0;cookies!=null&&i<cookies.length;i++){
    
    if(cookies[i].getName().equals("FlagImage")){
    
        username=cookies[i].getValue();
        count=count+1;
        
    }
    
if(cookies[i].getName().equals("user")){
        
        user=cookies[i].getValue();
        count2=count2+1;
        
    }
    
}

if(count!=0){
	if(username.equals("http://yourhost:yourport/adminwifipage-jaswanth-me")){
		System.out.print("success");
	}
	else{
		response.sendRedirect("fuckoff");
	}
}
else{
	response.sendRedirect("fuckoff");
}

if(count2==0){
	response.sendRedirect("welcome");
}

%>

<div style="margin-top: 300px;font-size: 30px;color: green;opacity: 0.6;"><center>You have logged on successfully into the CTFtIme Wifi.</center></div>
</body>
</html>