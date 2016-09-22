import excep.ConfictException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by orange on 16/9/21.
 */
public class PosValidation {


    //将id-时刻-坐标及变化值模块分割成单独个体,判断数据坐标格式是否有效
    public HashMap<String,String> readposList(ArrayList<String> list) throws Exception {
        HashMap<String,String> catPos = new HashMap<>();
        //通过格式验证的数据
        for (int i = 0; i < list.size(); i++) {
            //按行分割读取id-时刻-坐标
            String[] str = list.get(i).split("\n");
            for (int j = 2; j < str.length; j++) {
                String[] arr = str[j].split(" ");
                int n = arr.length;
                String animalId = arr[0];
                int posX = Integer.valueOf(arr[1]);
                int posY = Integer.valueOf(arr[2]);
                String pos = String.valueOf(posX)+" "+String.valueOf(posY);
                if (n>3){
                    //更新当前坐标x、y
                    posX = posX+Integer.valueOf(arr[3]);
                    posY = posY+Integer.valueOf(arr[4]);
                }
                if(catPos.containsKey(animalId)){
                    String value = catPos.get(animalId);
                    //比较上一时刻的pos与库中的pos是否一致
                    if(!value.equals(pos)){
                        throw new ConfictException(str[0]);
                    }
                }
                String pos2 = String.valueOf(posX)+" "+String.valueOf(posY);
                catPos.put(animalId,pos2);
            }
        }
        return catPos;
    }

    //将id-时刻-坐标及变化值模块分割成单独个体,判断数据坐标格式是否有效
    public String findposById(ArrayList<String> list,String id)throws Exception{
        //通过格式验证的数据
        for (int i = 0; i < list.size(); i++) {
            //按行分割读取id-时刻-坐标
            String[] str = list.get(i).split("\n");
            if (str[0].equals(id)){
                StringBuffer sb = new StringBuffer();
                for (int j = 2; j < str.length; j++) {
                    String[] arr = str[j].split(" ");
                    int n = arr.length;
                    String animalId = arr[0];
                    int posX = Integer.valueOf(arr[1]);
                    int posY = Integer.valueOf(arr[2]);
                    if (n>3){
                        //更新当前坐标x、y
                        posX = posX+Integer.valueOf(arr[3]);
                        posY = posY+Integer.valueOf(arr[4]);
                    }
                    String pos2 = String.valueOf(posX)+" "+String.valueOf(posY);
                    String tempPos = animalId+" "+pos2;
                    sb.append(tempPos+"\n");
                }
                return sb.toString();
            }
        }
        return "Invalid id.";
    }
}
