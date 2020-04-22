package CTFtIme.challenge1;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class passcode extends ActionSupport implements ServletRequestAware,ServletResponseAware  {

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
  
    public String getMd5(String input) throws NoSuchAlgorithmException {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext.substring(0,8);
        }

    public String execute()  throws NoSuchAlgorithmException, IOException{
    	
    	
    	String returnstr = "";
    	
    	
        String stamp =(String) request.getSession().getAttribute("secret");
        String[] separatesession = stamp.split("#");
        String cookiesecret="";
        Cookie cookies[]=request.getCookies();

        for(int i=0;i<cookies.length;i++){
    
            if(cookies[i].getName().equals("passcode")){
                cookiesecret=cookies[i].getValue();       
            }
    
        }

        if(cookiesecret.equals(separatesession[1])){

        returnstr = date(stamp, request.getParameter("passcode"));
        if (returnstr.equals("failure")) {

            setHeader(separatesession[1],separatesession[0],false);

        }
        
    }

        else{

          setHeader(separatesession[1],separatesession[0],true);      

        }

if(returnstr.equals("success")){
        	
	response.addHeader("Set-Cookie", "FlagImage=http://yourhost:yourport/adminwifipage-jaswanth-me");
	
        }
    	
        
    	resultJSONobj.put("result", returnstr);
    	System.out.println(resultJSONobj);
        
		
        return SUCCESS;
    }

            public String date(String stamp,String userhash)  throws NoSuchAlgorithmException{

                String[] separatesession = stamp.split("#");
                String time1 = separatesession[0];
                String result = "";
                String time2 = "";

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm@ss");
                time2 = simpleDateFormat.format(System.currentTimeMillis());

                String[] conf1 = time1.split("@");
                String[] conf2 = time2.split("@");
                System.out.println(conf1[0] + "   " + conf2[0] + "\n" + conf1[1] + "   " + conf2[1]);
                if (conf1[0].equals(conf2[0])) {
                    if (conf1[1].equals("00")) {
                        if (Integer.parseInt(conf2[1]) >= 0 && Integer.parseInt(conf2[1]) < 30) {
                            result = md5match(userhash, separatesession[1]);
                        } else {
                            result = "failure";
                        }

                    } else if (conf1[1].equals("30")) {
                        if (Integer.parseInt(conf2[1]) >= 30 && Integer.parseInt(conf2[1]) <= 59) {
                            result = md5match(userhash, separatesession[1]);
                        } else {
                            result = "failure";
                        }
                    }
                } else {
                    result = "failure";
                }

                return result;
            }

            public String md5match (String userhashed, String myhashed) throws NoSuchAlgorithmException{
                String msg = "";

                if (userhashed.equals(getMd5(myhashed))) {
                    msg = "success";
                } else {
                    msg = "failure";
                }

                return msg;
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
}
