

<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>
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
         
         ArrayList<String> status = new ArrayList<String>();
         

         ArrayList<String> allPosTweets = new ArrayList<String>();
         ArrayList<String> allNegTweets = new ArrayList<String>();
        
        %>
        
        
        <%
        
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
                    %>
                    <font color="blue" face="verdana" size = "6">
                 <%
                     allPosTweets.add(tweet);
                    // out.println(tweet);
                     //out.println("<br>");
                     
                     %></font><%
                }
            }              
        }


        for(int i=0;i<negDict.size();i++){
            String negKey = negDict.get(i);
            for(int j=0;j<allScatteredTweets.size();j++){
                String tweet = allScatteredTweets.get(j);
                if(tweet.contains(negKey)){
                    %>
                   <font color="red" face="verdana" size="6">
                 <%
                     allNegTweets.add(tweet) ;  
                     //out.println(tweet);
                     //out.println("<br>");
                     
                     %></font><%
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
                
                    %>
                    <font color="blue" face="verdana" size = "4">
                 <%
                     //allPosTweets.add(tweet);
                   out.println(tweet);
                     out.println("<br>");
                     
                     %></font><%
                }
                        
        


        
            for(int j=0;j<allNegTweets.size();j++){
                String tweet = allNegTweets.get(j);
                
                    %>
                   <font color="red" face="verdana" size="4">
                 <%
                     //allNegTweets.add(tweet) ;  
                     out.println(tweet);
                     out.println("<br>");
                     
                     %></font><%
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
        
        
        %>
    </center>
    </body>
</html>
