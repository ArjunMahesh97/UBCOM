

<%@page import="java.io.FileOutputStream"%>
<%@page import="GetData.GetAveragePerTweet"%>
<%@page import="GetData.GetRank"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.*"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="GetData.GetData"%>
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
         
         ArrayList<String> allEcomKeywords = new ArrayList<String>();
         ArrayList<String> myTweetsAfterFilteration = new ArrayList<String>();
         
         float redFreq = 0.0f;
         float blueFreq = 0.0f;
        
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
        FileInputStream fis = new FileInputStream("C:\\myfinal project\\datacenter\\samsung.txt");
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
        
        ArrayList<String> onlyGoodTweet = new ArrayList<String>();
        ArrayList<String> onlySpamTweet = new ArrayList<String>();
        onlyGoodTweet.clear();
        onlySpamTweet.clear();
        //myTweets.clear();
        //out.println(myTweetsAfterFilteration);
        //myTweets = myTweetsAfterFilteration;
        //
        for(int i=0;i<posDict.size();i++){
            String posKey = posDict.get(i);
            for(int j=0;j<myTweetsAfterFilteration.size();j++){
                String tweet = myTweetsAfterFilteration.get(j);
                if(tweet.contains(posKey)){
                    onlyGoodTweet.add(tweet);
                    float rank = new GetRank().getPosKeyWordRank(posKey, posDict);
                    goodTweets.add(tweet+"["+rank+"%]");
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
            for(int j=0;j<myTweetsAfterFilteration.size();j++){
                String tweet = myTweetsAfterFilteration.get(j);
                if(tweet.contains(negKey)){
                   onlySpamTweet.add(tweet);
                  float rank = new GetRank().getNegKeyWordRank(negKey, negDict);
                   spamTweets.add(tweet+"["+rank+"%]");
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


       int totalSizeOfTweets = goodTweets.size()+spamTweets.size();
       int totalSizeOfGoodTweets = goodTweets.size();
       int totalSizeOfBadTweets = spamTweets.size();
       
       float posPer = (float)totalSizeOfGoodTweets/totalSizeOfTweets*100;
       float negPer = (float)totalSizeOfBadTweets/totalSizeOfTweets*100;



       Set setGood = new HashSet(goodTweets);
       goodTweets.clear();
       goodTweets.addAll(setGood);

       Set setSpam = new HashSet(spamTweets);
       spamTweets.clear();
       spamTweets.addAll(setSpam);
       
       for(int i=0;i<goodTweets.size();i++){
          String tweet = goodTweets.get(i);
           %>
                   <font color="blue" face="verdana" size="4">
                 <%
                     //out.println(tweet);
                     //out.println("<br>");
                     
                     %></font><%
         }
        for(int i=0;i<spamTweets.size();i++){
          String tweet = spamTweets.get(i);
%>
           <font color="red" face="verdana" size="4">
                 <%
                     //out.println(tweet);
                     //out.println("<br>");
                     
                     %></font><%
        }
       // out.println("<br><br><br><br>"+posPer+":"+negPer);
        
     blueFreq = 0.00f;
     redFreq = 0.00f;
     int count=0;
     Set setGoodFinal = new HashSet(onlyGoodTweet);
     onlyGoodTweet.clear();
     onlyGoodTweet.addAll(setGoodFinal);
     for(int i=0;i<onlyGoodTweet.size();i++){
         String tempTweet = onlyGoodTweet.get(i);
         GetAveragePerTweet gt = new GetAveragePerTweet();
         float value = gt.getAveragePerTweet(goodTweets, tempTweet);
         blueFreq +=value;
         ++count;
         %> 
          <font color="blue" face="verdana" size="4">
         <%
         out.println(tempTweet+"["+value+"]");
         
          %></font> <%
         out.println("<br>");
       }
       blueFreq = blueFreq/count;
        count =0;
      out.println("<br>------------<br>");
      Set setBadFinal = new HashSet(onlySpamTweet);
      onlySpamTweet.clear();
      onlySpamTweet.addAll(setBadFinal);
 

      
     for(int i=0;i<onlySpamTweet.size();i++){
         String tempTweet = onlySpamTweet.get(i);
         GetAveragePerTweet gt = new GetAveragePerTweet();
         float value = gt.getAveragePerTweet(spamTweets, tempTweet);
         redFreq += value;
         ++count;
         %>
         <font color="red" face="verdana" size="4">
         <%
         out.println(tempTweet+"["+value+"]");
         %></font><%
         out.println("<br>");
       }
        redFreq = redFreq/count;
        count =0;
      FileOutputStream fosSamsung = new FileOutputStream("C:\\myfinal project\\datacenter\\samfreq.txt");
      String valueToUpdate = blueFreq+":"+redFreq;
      fosSamsung.write(valueToUpdate.getBytes());
      fosSamsung.close();

     
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        %>
    </center>
    </body>
</html>
