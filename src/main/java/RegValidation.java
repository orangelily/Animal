import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by orange on 16/9/21.
 */
public class RegValidation {

    public static boolean readId(String id) {

        if (id==null||id.trim().equals("")){
            return false;
        }
        //id正则表达式---验证非空字符串、不包含空格
        String regEx = "^[^\\s]*$";
        Pattern pattern = Pattern.compile(regEx);
        //id是否与正则表达式匹配
        Matcher matcher = pattern.matcher(id);
        boolean rs = matcher.matches();
        if (!rs) {
            return false;
        }
        return true;
    }

    public static boolean readDate(String str) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
        return true;
    }

    public static boolean readPos(String str){
        String[] arr = str.split(" ");
        int n = arr.length;
        /* 坐标变换格式
            cat1 10 9 2 -1
            cat2 2 3
         */
        if (n!=3&&n!=5){
            return false;
        }
        try {
            String animalId = arr[0];
            int posX = Integer.valueOf(arr[1]);
            int posY = Integer.valueOf(arr[2]);
            if (n>3){
                //更新当前坐标x、y
                posX = posX+Integer.valueOf(arr[3]);
                posY = posX+Integer.valueOf(arr[4]);
            }

        }catch (NumberFormatException e){
            //数据转换出错
            return false;
        }

        return true;
    }

    //将id-时刻-坐标及变化值模块分割成单独个体,判断格式是否有效readList
    public boolean readList(ArrayList<String> list)throws Exception{
        //验证数据格式是否正确
        if (list!=null){
            for (int i = 0; i < list.size(); i++) {
                //按行分割读取id-时刻-坐标
                String[] str = list.get(i).split("\n");
                if (str.length>=3) {
                    String id = str[0];
                    //id是否与正则表达式匹配
                    boolean rs = readId(id);
                    if (!readId(id)||!readDate(str[1])){
                        return false;
                    }
                    for (int j = 2; j < str.length; j++) {
                        if (!readPos(str[j])){
                            return false;
                        }
                    }
                }else {
                    //缺少数据 不是合理的id-时刻-坐标
                    return false;
                }
            }
        }else {
            //数据为空
            return false;
        }
        return true;
    }


    //去除空行分割字符串为id-时刻-坐标及变化值
    public ArrayList<String> readHistoryData(String historyData) throws Exception {
        //存储historyData

        String[] str = historyData.split("\n");
        ArrayList<String> list = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            //去除空行
            String temp = str[i].trim();
            //读取字符串为坐标,表示需要分割成新的id-时刻-坐标
            if (temp!=null&&readId(temp)&&sb.length()>0){
                list.add(sb.toString());
                //清空原来的数据,构建新的数据
                sb = sb.delete(0,sb.length());
                sb.append(temp+"\n");
            }else if (temp!=null&&!(temp.equals(""))){
                //属于一个id下的时刻及坐标信息
                sb.append(temp+"\n");
            }else{
                continue;
            }
        }
        list.add(sb.toString());
        return list;
    }

    //将id-时刻-坐标及变化值模块分割成单独个体,判断时间是否有效,无重复值
    public boolean readtimeList(ArrayList<String> list) {
        HashMap<String,String> catTime = new HashMap<>();
        //通过格式验证的数据
        for (int i = 0; i < list.size(); i++) {
            //按行分割读取id-时刻-坐标
            String[] str = list.get(i).split("\n");
            if (catTime.containsKey(str[1])) {
                return false;
            }else {
                catTime.put(str[1],str[0]);
            }
        }
        return true;
    }
}
