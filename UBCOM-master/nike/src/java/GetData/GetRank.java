/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GetData;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Ankush Lakkanna
 */
public class GetRank {
    float rank = 0.0f;
    public float getPosKeyWordRank(String keyWord,ArrayList<String> posKeywords){
        try{
        ArrayList<Integer> allRanks = new ArrayList<Integer>();
        allRanks.clear();
        FileInputStream fis = new FileInputStream("C:\\myfinal project\\svm\\posrank.txt");
        byte bb[] = new byte[fis.available()];
        fis.read(bb);
        fis.close();
        String data = new String(bb);
        data = data.trim();
        StringTokenizer st = new StringTokenizer(data,"\r\n");
        while(st.hasMoreTokens()){
          allRanks.add(Integer.parseInt(st.nextToken()));  
        }
        int index = posKeywords.indexOf(keyWord);
        int rankTemp =  allRanks.get(index);
        rank = (float)rankTemp;
        }catch(Exception e){
            System.out.println(e);
        }
        
        return rank;
    }
    public float getNegKeyWordRank(String keyWord,ArrayList<String> negKeywords){
        try{
        ArrayList<Integer> allRanks = new ArrayList<Integer>();
        allRanks.clear();
        FileInputStream fis = new FileInputStream("C:\\myfinal project\\svm\\negrank.txt");
        byte bb[] = new byte[fis.available()];
        fis.read(bb);
        fis.close();
        String data = new String(bb);
        data = data.trim();
        StringTokenizer st = new StringTokenizer(data,"\r\n");
        while(st.hasMoreTokens()){
          allRanks.add(Integer.parseInt(st.nextToken()));  
        }
        int index = negKeywords.indexOf(keyWord);
        int rankTemp =  allRanks.get(index);
        rank = (float) rankTemp;
        }catch(Exception e){
            System.out.println(e);
        }
        
        return rank;
    }
    
}
