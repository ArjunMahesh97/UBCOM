/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.first;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Ankush Lakkanna
 */
@WebService(serviceName = "firststorage")
public class firststorage {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        try{
        FileInputStream fis = new FileInputStream("C:\\myfinal project\\datacenter\\first.txt");
        byte bb[] = new byte[fis.available()];
        fis.read(bb);
        fis.close();
        
        if(bb.length==0){
            FileOutputStream fos = new FileOutputStream("C:\\myfinal project\\datacenter\\first.txt");
            fos.write(txt.getBytes());
            fos.close();
        }
        else{
           String dataTweets = new String(bb);
           String toUpdate = dataTweets+"\r\n"+dataTweets;
           FileOutputStream fos = new FileOutputStream("C:\\myfinal project\\datacenter\\first.txt");
            fos.write(toUpdate.getBytes());
            fos.close();
        }
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        return "Tweet updated to first Cloud";
    }
}
