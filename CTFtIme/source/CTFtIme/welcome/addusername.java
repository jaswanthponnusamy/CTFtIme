package CTFtIme.welcome;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class addusername extends ActionSupport implements ServletRequestAware,ServletResponseAware {

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

public String execute(){

String reqmethod=request.getMethod();
System.out.println(reqmethod);

if(!reqmethod.equals("POST")){

return "405";

}

String username=request.getParameter("username");
response.addHeader("Set-Cookie","user="+username);

return "success";

}

}	