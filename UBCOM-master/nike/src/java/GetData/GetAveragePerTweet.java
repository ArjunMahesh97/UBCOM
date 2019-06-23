/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GetData;

import java.io.*;
import java.util.*;
public class GetAveragePerTweet {
    float percentage = 0.0f;
    public float getAveragePerTweet(ArrayList<String> allTweetsWithPercentage,String tweet){
        
        try{
            float aggreate = 0.0f;
            int count = 0;
        for(int i=0;i<allTweetsWithPercentage.size();i++){
            
            String tweetFromList = allTweetsWithPercentage.get(i);
            tweetFromList = tweetFromList.trim();
            if(tweetFromList.startsWith(tweet)){
                String onlyPercentage = tweetFromList.substring(tweetFromList.lastIndexOf("[")+1,tweetFromList.length()-2);
                aggreate += Float.parseFloat(onlyPercentage);
                ++count;
            }
        }
        percentage = aggreate/count;
        }catch(Exception e){
            System.out.println(e);
        }
                
        return percentage;
    }
    
}
