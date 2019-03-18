/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GetData;

import java.util.*;
public class Testing {
    
    public static void main(String []args){
        GetData gd = new GetData();
        ArrayList<String> allTerms = new ArrayList<String>();
        ArrayList<Integer> allTermsPriorities = new ArrayList<Integer>();
        allTermsPriorities.clear();
        allTerms.clear();
        allTerms.add("not graceful");
        allTerms.add("not good");
        
        allTermsPriorities = gd.getAllpriorities("C:\\myfinal project\\svm\\svmnegweight.txt");
        GetData gd1 = new GetData();
        System.out.println(gd.getPriorityItem("not good",allTermsPriorities,allTerms));
        
        
    }
    
}
