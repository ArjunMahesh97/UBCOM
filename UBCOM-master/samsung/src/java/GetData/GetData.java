
package GetData;
import java.io.*;
import java.util.*;
public class GetData {
    ArrayList<Integer> allPriorities = new ArrayList<Integer>();
    int priorityOfItem = 0;
    public ArrayList<Integer> getAllpriorities(String path){
        allPriorities.clear();
        try{
        FileInputStream fis = new FileInputStream(path);
        byte bb[] = new byte[fis.available()];
        fis.read(bb);
        fis.close();
        
        String data = new String(bb);
        data = data.trim();
        StringTokenizer st = new StringTokenizer(data,"\r\n");
        while(st.hasMoreTokens()){
            String token = st.nextToken();
            int priority = Integer.parseInt(token);
            allPriorities.add(priority);
        }
        }catch(Exception e){
            System.out.println(e);
        }
        return allPriorities;
    }
    
    public int getPriorityItem(String item,ArrayList<Integer> priorityValues,ArrayList<String> items){
        try{
        
        int index = items.indexOf(item);
        priorityOfItem = priorityValues.get(index);
        }catch(Exception e){
            System.out.println(e);
        }
        return priorityOfItem;
    }
    
}
