import excep.ConfictException;
import model.Animal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by orange on 16/9/20.
 */
public class CatAnalysis {

    public static void main(String[] args) throws Exception {
        System.out.println("读取historyData.txt,控制台输入id:");
        Scanner cin = new Scanner(System.in);
        //保存要查找的id
        String id = cin.nextLine();
        /*
        String path = CatAnalysis.class.getResource("").getPath();
        System.out.println(path);
        */
        String historyData = readHistoryData("/historyData.txt");
        String output = getSnapShot(historyData, id);
        System.out.println(output);
    }

    public static String getSnapShot(String historyData, String id) throws Exception {
        RegValidation reg = new RegValidation();
        ArrayList<String> list = reg.readHistoryData(historyData);
        StringBuilder output = new StringBuilder();
        ArrayList<Animal> animals = new ArrayList<>();
        if (!reg.readList(list)) {
            return "Invalid format.";
        }
        if (!reg.readtimeList(list)) {
            return "Invalid time";
        }

        PosValidation pos = new PosValidation();
        try {
            HashMap<String,String> res = pos.readposList(list);
            //匹配id与historyData
            if (!reg.readId(id)) {
                return "Invalid search id.";
            } else {
                //找到id对应的数据
                Iterator<Map.Entry<String,String>> entry = res.entrySet().iterator();
                while (entry.hasNext()){
                    Map.Entry<String,String> temp = entry.next();
                    Animal a = new Animal(temp.getKey(),temp.getValue());
                    animals.add(a);
                }
                Animal[] animalsArr = animals.toArray(new Animal[animals.size()]);
                Arrays.sort(animalsArr);

                for (int i = 0; i < animalsArr.length; i++) {
                    output.append(animalsArr[i].getAnimalId()+" "+animalsArr[i].getPos()+"\n");
                }
            }
        }catch (ConfictException e){
            return "Conflict found at " + e.getMessage();
        }

        return output.toString();
    }

    public static String readHistoryData(String filePath) throws IOException {
        //保存historyData
        StringBuilder historyData = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(CatAnalysis.class.getResourceAsStream(filePath));
        BufferedReader bfr = new BufferedReader(inputStreamReader);

        String temp = "";
        while ((temp = bfr.readLine()) != null) {
            historyData.append(temp+"\n");
        }
        bfr.close();
        return historyData.toString();
}

}
