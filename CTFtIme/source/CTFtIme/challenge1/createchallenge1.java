package CTFtIme.challenge1;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class createchallenge1 extends ActionSupport implements ServletRequestAware,ServletResponseAware  {

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

String returnstr = "success";
String stamp = (String) request.getSession().getAttribute("secret");
if(stamp==null){

nullbase();

}

else{

		String[] separatesession = stamp.split("#");
        String cookiesecret="";
        Cookie cookies[]=request.getCookies();

        for(int i=0;i<cookies.length;i++){
    
            if(cookies[i].getName().equals("passcode")){
                cookiesecret=cookies[i].getValue();       
            }
    
        }

        if(cookiesecret.equals(separatesession[1])){

        	setHeader(separatesession[1],separatesession[0],false);

        }

        else{

        	setHeader(separatesession[1],separatesession[0],true);

        }


}

 return returnstr;
}

public void setHeader(String stamp,String time1,boolean flag){

                String time2 = "";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm@ss");
                long timestamp=System.currentTimeMillis();
                time2 = simpleDateFormat.format(timestamp);
                System.out.print("time1 : "+time1+"\n");
                System.out.print("time2 : "+time2);
                String[] conf1 = time1.split("@");
                String[] conf2 = time2.split("@");

                if (conf1[0].equals(conf2[0])) {
                    if (conf1[1].equals("00")) {

                        if (Integer.parseInt(conf2[1]) >= 0 && Integer.parseInt(conf2[1]) < 30) {
                            if(flag){

                                response.addHeader("Set-Cookie","passcode="+stamp);
                                response.addHeader("Set-Cookie","sol="+"YWxnIGhhc2ggLSBtZDUoKQ==");
                            }
                        } else {
                            long stamp2=System.currentTimeMillis();
                           System.out.print("coming 00");
                            response.addHeader("Set-Cookie","passcode="+stamp2);
                            response.addHeader("Set-Cookie","sol="+"YWxnIGhhc2ggLSBtZDUoKQ==");
                            request.getSession().setAttribute("secret",conf2[0]+"@30#"+stamp2);
                        }

                    } else if (conf1[1].equals("30")) {
                        if (Integer.parseInt(conf2[1]) > 30 && Integer.parseInt(conf2[1]) <= 59) {
                            System.out.println("no prob");
                        } else {
                        	System.out.print("coming 30");
                        	String time3="";
                            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm");
                            time3 = simpleDateFormat2.format(timestamp+(1*60*1000));
                            long stamp2=System.currentTimeMillis();

                            response.addHeader("Set-Cookie","passcode="+stamp2);
                            response.addHeader("Set-Cookie","sol="+"YWxnIGhhc2ggLSBtZDUoKQ==");
                            request.getSession().setAttribute("secret",time3+"@00#"+stamp2);
                            
                        }
                    }


                }

                else{
                	
                	if (Integer.parseInt(conf2[1]) >= 0 && Integer.parseInt(conf2[1]) < 30) {
                		
                            long stamp2=System.currentTimeMillis();

                            response.addHeader("Set-Cookie","passcode="+stamp2);
                            response.addHeader("Set-Cookie","sol="+"YWxnIGhhc2ggLSBtZDUoKQ==");
                            request.getSession().setAttribute("secret",conf2[0]+"@00#"+stamp2);
                	}
                		
                	else{

                	long stamp2=System.currentTimeMillis();
                           
                    response.addHeader("Set-Cookie","passcode="+stamp2);
                    response.addHeader("Set-Cookie","sol="+"YWxnIGhhc2ggLSBtZDUoKQ==");
                    request.getSession().setAttribute("secret",conf2[0]+"@30#"+stamp2);
                }

                }

            }


            public void nullbase(){



        String time5="";
        long timestamp=System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat5 = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm@ss");
        time5 = simpleDateFormat5.format(timestamp);
                String[] conf2 = time5.split("@");


                if (Integer.parseInt(conf2[1]) >= 0 && Integer.parseInt(conf2[1]) < 30) {
                    String time4="";
                    SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm");
                    time4 = simpleDateFormat4.format(timestamp+(1*60*1000));
                    long stamp2=System.currentTimeMillis();

                    response.addHeader("Set-Cookie","passcode="+stamp2);
                    response.addHeader("Set-Cookie","sol="+"YWxnIGhhc2ggLSBtZDUoKQ==");
                    request.getSession().setAttribute("secret",time4+"@00#"+stamp2);
                }

                else{

                    long stamp2=System.currentTimeMillis();

                    response.addHeader("Set-Cookie","passcode="+stamp2);
                    response.addHeader("Set-Cookie","sol="+"YWxnIGhhc2ggLSBtZDUoKQ==");
                    request.getSession().setAttribute("secret",conf2[0]+"@30#"+stamp2);
                }

            }

        }




