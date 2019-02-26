/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sp1;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.io.*;
import java.util.*;

/**
 *
 * @author Win
 */
@WebService(serviceName = "spcloud")
public class spcloud {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        ArrayList<String> allTweets = new ArrayList<String>();
        try{
        allTweets.clear();
        FileInputStream fis = new FileInputStream("C:\\myfinal project\\datacenter\\normal.txt");
        byte bb[]= new byte[fis.available()];
        fis.read(bb);
        fis.close();
        String data = "";
        
        if(bb.length>0){
//            data = new String(bb);
//            StringTokenizer st = new StringTokenizer(data,"\r\n");
//            while(st.hasMoreTokens()){
//                allTweets.add(st.nextToken());
//            }
            data = new String(bb);
            data +="\r\n"+ txt;
           FileOutputStream fos = new FileOutputStream("C:\\myfinal project\\datacenter\\normal.txt"); 
           fos.write(data.getBytes());
           fos.close();
        }
        else{
            FileOutputStream fos = new FileOutputStream("C:\\myfinal project\\datacenter\\normal.txt"); 
           fos.write(txt.getBytes());
           fos.close();
        }
         
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        return "Hello " +txt + " !";
    }
}
