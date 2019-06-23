<%-- 
    Document   : tweets
    Created on : Apr 24, 2019, 5:26:53 PM
    Author     : Ankush Lakkanna
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <center>
     <body bgcolor="pink">
        <font color="blue" size="4" face="verdana">
        <%!
        ArrayList<String> allTweets = new ArrayList<String>();
        %>
       <%try{
           response.setIntHeader("Refresh", 5);
           allTweets.clear();
           FileInputStream fis = new FileInputStream("C:\\myfinal project\\datacenter\\nandini.txt");
           byte bb[] = new byte[fis.available()];
           fis.read(bb);
           fis.close();
           String tweets = new String(bb);
           StringTokenizer st = new StringTokenizer(tweets,"\r\n");
           while(st.hasMoreTokens()){
               allTweets.add(st.nextToken());
           }
           Collections.reverse(allTweets);
           
           if(allTweets.size()!=0){
              for(int i=0;i<allTweets.size();i++){
                  out.println(allTweets.get(i));
                  out.println("<br>");
              } 
           }
       }catch(Exception e){
           
           out.println(e);
       }%>
    
    </font>
    
    
    </center>
    </body>
</html>
