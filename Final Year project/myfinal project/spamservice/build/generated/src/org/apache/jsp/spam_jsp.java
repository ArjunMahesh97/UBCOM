package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.StringTokenizer;
import java.util.*;
import java.io.FileInputStream;
import GetData.GetData;

public final class spam_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


         ArrayList<String> myTweets = new ArrayList<String>();
         ArrayList<String> posDict = new ArrayList<String>();
         ArrayList<String> negDict = new ArrayList<String>();
         
         ArrayList<String> posSvmDict = new ArrayList<String>();
         ArrayList<String> negSvmDict = new ArrayList<String>();
         
         ArrayList<String> goodTweets = new ArrayList<String>();
         ArrayList<String> spamTweets = new ArrayList<String>();
         
         ArrayList<String> status = new ArrayList<String>();
         
         ArrayList<String> allEcomKeywords = new ArrayList<String>();
         ArrayList<String> myTweetsAfterFilteration = new ArrayList<String>();
        
        
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body bgcolor=\"pink\">\n");
      out.write("    <center>\n");
      out.write("        \n");
      out.write("        ");
      out.write("\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        ");

        
        try{
         posDict.clear();
         negDict.clear();
         status.clear();
         goodTweets.clear();
         spamTweets.clear();
         posSvmDict.clear();
         negSvmDict.clear();
         //pos
         FileInputStream fisPos = new FileInputStream("C:\\myfinal project\\svm\\pos.txt");
         byte bbPos[] = new byte[fisPos.available()];
         fisPos.read(bbPos);
         fisPos.close();
         String strPos = new String(bbPos);
         strPos = strPos.trim();
         StringTokenizer stPos = new StringTokenizer(strPos,"\r\n");
         while(stPos.hasMoreTokens()){
           posDict.add(stPos.nextToken());  
         }
         //pos
         
         //neg
         FileInputStream fisNeg = new FileInputStream("C:\\myfinal project\\svm\\neg.txt");
         byte bbNeg[] = new byte[fisNeg.available()];
         fisNeg.read(bbNeg);
         fisNeg.close();
         String strNeg = new String(bbNeg);
         strNeg = strNeg.trim();
         StringTokenizer stNeg = new StringTokenizer(strNeg,"\r\n");
         while(stNeg.hasMoreTokens()){
           negDict.add(stNeg.nextToken());  
         }
         //neg
         
         FileInputStream fisSvmPos = new FileInputStream("C:\\myfinal project\\svm\\svmpos.txt") ;        
         byte bbSvmPos[] = new byte[fisSvmPos.available()];
         fisSvmPos.read(bbSvmPos);
         fisSvmPos.close();
         String strSvmPos = new String(bbSvmPos);
         strSvmPos = strSvmPos.trim();
         StringTokenizer stSvmPos = new StringTokenizer(strSvmPos,"\r\n");
         while(stSvmPos.hasMoreElements()){
            posSvmDict.add(stSvmPos.nextToken()) ;
         }
         FileInputStream fisSvmNeg = new FileInputStream("C:\\myfinal project\\svm\\svmneg.txt") ;        
         byte bbSvmNeg[] = new byte[fisSvmNeg.available()];
         fisSvmNeg.read(bbSvmNeg);
         fisSvmNeg.close();
         String strSvmNeg = new String(bbSvmNeg);
         strSvmNeg = strSvmNeg.trim();
         StringTokenizer stSvmNeg = new StringTokenizer(strSvmNeg,"\r\n");
         while(stSvmNeg.hasMoreElements()){
            negSvmDict.add(stSvmNeg.nextToken()) ;
         }               
            
             
        response.setIntHeader("Refresh", 5);
        FileInputStream fis = new FileInputStream("C:\\myfinal project\\datacenter\\normal.txt");
        byte bb[] = new byte[fis.available()];
        fis.read(bb);
        fis.close();
       
        myTweets.clear();
        String data = new String(bb);
        StringTokenizer st = new StringTokenizer(data,"\r\n");
        while(st.hasMoreTokens()){
           myTweets.add(st.nextToken()); 
        }
        //out.println(myTweets);
        GetData gd = new GetData();
        
        allEcomKeywords.clear();
        FileInputStream fisEcom = new FileInputStream("C:\\myfinal project\\svm\\ecomm.txt");
        byte bbEcom[] = new byte[fisEcom.available()];
        fisEcom.read(bbEcom);
        fisEcom.close();
        String myEcomData = new String(bbEcom);
        myEcomData = myEcomData.trim();
        StringTokenizer stEcom = new StringTokenizer(myEcomData,"\r\n");
        while(stEcom.hasMoreTokens()){
          allEcomKeywords.add(stEcom.nextToken());  
        }
        
        //
        myTweetsAfterFilteration.clear();
        for(int i=0;i<myTweets.size();i++){
            String tweet = myTweets.get(i);
            for(int j=0;j<allEcomKeywords.size();j++){
                String ecomKey = allEcomKeywords.get(j);
               if(tweet.contains(ecomKey)){
                  myTweetsAfterFilteration.add(tweet); 
               } 
            }
        }
        Set set = new HashSet(myTweetsAfterFilteration);
        myTweetsAfterFilteration.clear();
        myTweetsAfterFilteration.addAll(set);
        //myTweets.clear();
        //out.println(myTweetsAfterFilteration);
        //myTweets = myTweetsAfterFilteration;
        //
        for(int i=0;i<posDict.size();i++){
            String posKey = posDict.get(i);
            for(int j=0;j<myTweetsAfterFilteration.size();j++){
                String tweet = myTweetsAfterFilteration.get(j);
                if(tweet.contains(posKey)){
                    goodTweets.add(tweet);
                    
      out.write("\n");
      out.write("                   <font color=\"blue\" face=\"verdana\">\n");
      out.write("                 ");

                     //out.println(tweet);
                     //out.println("<br>");
                     
                     
      out.write("</font>");

                }
            }              
        }


        for(int i=0;i<negDict.size();i++){
            String negKey = negDict.get(i);
            for(int j=0;j<myTweetsAfterFilteration.size();j++){
                String tweet = myTweetsAfterFilteration.get(j);
                if(tweet.contains(negKey)){
                   spamTweets.add(tweet);
                    
      out.write("\n");
      out.write("                   <font color=\"red\" face=\"verdana\">\n");
      out.write("                 ");

                     //out.println(tweet);
                     //out.println("<br>");
                     
                     
      out.write("</font>");

                }
            }              
        }
        
        
        //
        for(int i=0;i<negSvmDict.size();i++){
           String negKey = negSvmDict.get(i);
          for(int j=0;j<goodTweets.size();j++){
           String tweet = goodTweets.get(j);
              if(tweet.contains(negKey)){
               goodTweets.remove(j);
             }
           }
         }

        for(int i=0;i<posSvmDict.size();i++){
          String posKey = posSvmDict.get(i);
          for(int j=0;j<spamTweets.size();j++){
            String tweet = spamTweets.get(j);
            if(tweet.contains(posKey)){
              spamTweets.remove(j);
             }
           }
        }
        //

    
       for(int i=0;i<goodTweets.size();i++){
          String tweet = goodTweets.get(i);
           
      out.write("\n");
      out.write("                   <font color=\"blue\" face=\"verdana\" size=\"6\">\n");
      out.write("                 ");

                     out.println(tweet);
                     out.println("<br>");
                     
                     
      out.write("</font>");

         }
        for(int i=0;i<spamTweets.size();i++){
          String tweet = spamTweets.get(i);

      out.write("\n");
      out.write("           <font color=\"red\" face=\"verdana\" size=\"6\">\n");
      out.write("                 ");

                     out.println(tweet);
                     out.println("<br>");
                     
                     
      out.write("</font>");

        }
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        
      out.write("\n");
      out.write("    </center>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
