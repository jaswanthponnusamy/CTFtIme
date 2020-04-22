<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>CTFtIme</title>
</head>
<body>
<%String username="";
Cookie cookies[]=request.getCookies();
int count=0;
String user="";
int count2=0;
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
		response.sendRedirect("Successwifi");
	}
	else{
		response.sendRedirect("fuckoff");
	}
}

if(count2==0){
	response.sendRedirect("welcome");
}

%>

<div><center style="font-size: 50px;font-family: sans-serif;">Wifi Login</center></div>
<img src="<%=request.getContextPath() %>/images/download.png" height="300px" style="margin-left: 600px;
    margin-top: 50px;">
<div style="font-size: 40px;margin-top: 30px;"><div><center>This is official website to login into</center></div><div><center>the wifi by administrators.</center></div><div><center>Can you crack this wifi passcode?</center></div></div>
<div style="margin-top: 60px;margin-left:390px;"><span style="font-size: 30px;font-family: sans-serif;">please submit the passcode here : </span>
<input type="text" name="passcode" id="passcode" placeholder="wifi passcode.." maxlength="8" minlength="1" style="width: 300px;height: 30px;font-size: 18px;border-color: #212529;border-style: solid;opacity: 0.5;font-family: sans-serif;">
<button type="submit" onclick="myfun()" style="letter-spacing: 1px;
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
    ">submit</button>
</div>
<div id="result" style="opacity: 0.7;margin-left: 980px;
    margin-top: 30px;
    font-size: 30px;
    font-family: sans-serif;
    font-weight: 600;"></div>

<script type="text/javascript">
document.getElementById("result").style.display="none";
function myfun(){
var xhr=new XMLHttpRequest();
var data="?passcode="+document.getElementById("passcode").value; 
var url="<%=request.getContextPath() %>/passcode"+data;

xhr.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200){
		
		
		var obj=JSON.parse(this.responseText);
		if(obj.result=="success"){
			document.getElementById("result").style.color="green";
			document.getElementById("passcode").style.borderColor="green";
		}
		else{
			document.getElementById("result").style.color="red";
			document.getElementById("passcode").style.borderColor="red";
		}
		document.getElementById("result").innerHTML = obj.result;
		document.getElementById("result").style.display="";
		
		setTimeout(function(){
		    document.getElementById("result").innerHTML = '';
		    document.getElementById("result").style.display="none";
		    location.reload();
		}, 1000);
		
	}
}


xhr.open("GET",url,true);
xhr.send();

}


</script>

</body>
</html>