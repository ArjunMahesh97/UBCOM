

<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.FileInputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body bgcolor="pink">
    <center>
        
        <%!
         ArrayList<String> myTweets = new ArrayList<String>();
         ArrayList<String> posDict = new ArrayList<String>();
         ArrayList<String> negDict = new ArrayList<String>();
         
         ArrayList<String> posSvmDict = new ArrayList<String>();
         ArrayList<String> negSvmDict = new ArrayList<String>();
         
         ArrayList<String> goodTweets = new ArrayList<String>();
         ArrayList<String> spamTweets = new ArrayList<String>();
         
         ArrayList<String> status = new ArrayList<String>();
        
        %>
        
        
        <%
        
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
        
        
        for(int i=0;i<posDict.size();i++){
            String posKey = posDict.get(i);
            for(int j=0;j<myTweets.size();j++){
                String tweet = myTweets.get(j);
                if(tweet.contains(posKey)){
                    goodTweets.add(tweet);
                    %>
                   <font color="blue" face="verdana">
                 <%
                     //out.println(tweet);
                     //out.println("<br>");
                     
                     %></font><%
                }
            }              
        }


        for(int i=0;i<negDict.size();i++){
            String negKey = negDict.get(i);
            for(int j=0;j<myTweets.size();j++){
                String tweet = myTweets.get(j);
                if(tweet.contains(negKey)){
                   spamTweets.add(tweet);
                    %>
                   <font color="red" face="verdana">
                 <%
                     //out.println(tweet);
                     //out.println("<br>");
                     
                     %></font><%
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
           %>
                   <font color="blue" face="verdana" size="6">
                 <%
                     out.println(tweet);
                     out.println("<br>");
                     
                     %></font><%
         }
        for(int i=0;i<spamTweets.size();i++){
          String tweet = spamTweets.get(i);
%>
           <font color="red" face="verdana" size="6">
                 <%
                     out.println(tweet);
                     out.println("<br>");
                     
                     %></font><%
        }
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        %>
    </center>
    </body>
</html>
