package CTFtIme.challengecompletion;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class challenge1submit extends ActionSupport implements ServletRequestAware,ServletResponseAware  {

private HttpServletRequest request;
  private HttpServletResponse response;

public void setServletRequest(HttpServletRequest request){
  this.request = request;
  }

  public HttpServletRequest getServletRequest(){
  return request;
  }

  public void setServletResponse(HttpServletResponse response){
  this.response = response;
  }

  public HttpServletResponse getServletResponse(){
  return response;
  }

  private Map resultJSONobj = new HashMap(); 


public Map getResultJSONobj() {
   return resultJSONobj;
}
public void setResultJSONobj(Map resultJSONobj) {
   this.resultJSONobj = resultJSONobj;
}

public String execute(){

String usersubmittedflag=request.getParameter("flag");
String result="";

if(usersubmittedflag.equals("CTFtIme{WeB_iS_HArDeR}")){

result="success";
request.getSession().setAttribute("challenge1","true");

}

else{

result="failure";

}

resultJSONobj.put("result", result);
System.out.println(resultJSONobj);
        

 return SUCCESS;
}

}

