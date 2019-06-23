package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Set;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.*;

public final class MAINUI_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


         ArrayList<String> myTweets = new ArrayList<String>();
         ArrayList<String> posDict = new ArrayList<String>();
         ArrayList<String> negDict = new ArrayList<String>();
         
         ArrayList<String> status = new ArrayList<String>();
         

         ArrayList<String> allPosTweets = new ArrayList<String>();
         ArrayList<String> allNegTweets = new ArrayList<String>();
        
        
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
         allPosTweets.clear();
         allNegTweets.clear();
         
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
        
        ArrayList<String> allScatteredTweets = new ArrayList<String>();
        allScatteredTweets.clear();
        allScatteredTweets = (ArrayList<String>)myTweets.clone();
        
        Set set = new HashSet(allScatteredTweets);
        allScatteredTweets.clear();
        allScatteredTweets.addAll(set);
           //out.println(allScatteredTweets.size()+":"+myTweets.size());     
        //
        
        //
        
        
        for(int i=0;i<posDict.size();i++){
            String posKey = posDict.get(i);
            for(int j=0;j<allScatteredTweets.size();j++){
                String tweet = allScatteredTweets.get(j);
                if(tweet.contains(posKey)){
                    
      out.write("\n");
      out.write("                    <font color=\"blue\" face=\"verdana\" size = \"6\">\n");
      out.write("                 ");

                     allPosTweets.add(tweet);
                    // out.println(tweet);
                     //out.println("<br>");
                     
                     
      out.write("</font>");

                }
            }              
        }


        for(int i=0;i<negDict.size();i++){
            String negKey = negDict.get(i);
            for(int j=0;j<allScatteredTweets.size();j++){
                String tweet = allScatteredTweets.get(j);
                if(tweet.contains(negKey)){
                    
      out.write("\n");
      out.write("                   <font color=\"red\" face=\"verdana\" size=\"6\">\n");
      out.write("                 ");

                     allNegTweets.add(tweet) ;  
                     //out.println(tweet);
                     //out.println("<br>");
                     
                     
      out.write("</font>");

                }
            }              
        }


        Set posSet = new HashSet(allPosTweets);
        allPosTweets.clear();
        allPosTweets.addAll(posSet);

        Set negSet = new HashSet(allNegTweets);
        allNegTweets.clear();
        allNegTweets.addAll(negSet);

           
        
           
            for(int j=0;j<allPosTweets.size();j++){
                String tweet = allPosTweets.get(j);
                
                    
      out.write("\n");
      out.write("                    <font color=\"blue\" face=\"verdana\" size = \"4\">\n");
      out.write("                 ");

                     //allPosTweets.add(tweet);
                   out.println(tweet);
                     out.println("<br>");
                     
                     
      out.write("</font>");

                }
                        
        


        
            for(int j=0;j<allNegTweets.size();j++){
                String tweet = allNegTweets.get(j);
                
                    
      out.write("\n");
      out.write("                   <font color=\"red\" face=\"verdana\" size=\"4\">\n");
      out.write("                 ");

                     //allNegTweets.add(tweet) ;  
                     out.println(tweet);
                     out.println("<br>");
                     
                     
      out.write("</font>");

                }
            
        
//        FileOutputStream fosP = new FileOutputStream("C:\\pos.ser");
//        ObjectOutputStream osP = new ObjectOutputStream(fosP);
//        osP.writeObject(allPosTweets);
//        osP.close();
//        fosP.close();
//
//        FileOutputStream fosN = new FileOutputStream("C:\\neg.ser");
//        ObjectOutputStream osN = new ObjectOutputStream(fosN);
//        osN.writeObject(allNegTweets);
//        osN.close();
//        fosN.close();
        
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
