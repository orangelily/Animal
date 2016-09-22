import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by orange on 16/9/21.
 */
public class RegValidationTest {

    @Test
    public void id_test_with_empty_string(){
        //验证字符串id的正则表达式
        RegValidation reg = new RegValidation();
        String id = "  ";
        Assert.assertFalse(reg.readId(id));
    }

    @Test
    public void id_test_string_with_blank(){
        //验证字符串id的正则表达式
        RegValidation reg = new RegValidation();
        String id = "aa  ";
        Assert.assertFalse(reg.readId(id));
    }

    @Test
    public void id_test_string_with_blank_in(){
        //验证字符串id的正则表达式
        RegValidation reg = new RegValidation();
        String id = "aa  aa";
        Assert.assertFalse(reg.readId(id));
    }

    @Test
    public void id_test_string_with_noblank(){
        //验证字符串id的正则表达式
        RegValidation reg = new RegValidation();
        String id = "aa-aa";
        Assert.assertTrue(reg.readId(id));
    }

    @Test
    public void id_test_string_with_diff(){
        //验证字符串id的正则表达式
        RegValidation reg = new RegValidation();
        String id = "e4e87cb2-8e9a-4749-abb6-26c59344dfee";
        Assert.assertTrue(reg.readId(id));
    }


    @Test
    public void date_test_string_with_string()throws Exception{
        //验证时间格式是否正确
        RegValidation reg = new RegValidation();
        String date = "sss";
        Assert.assertFalse(reg.readDate(date));
    }
    @Test
    public void date_test_string_with_number()throws Exception{
        //验证时间格式是否正确
        RegValidation reg = new RegValidation();
        String date = "2017";
        Assert.assertFalse(reg.readDate(date));
    }

    @Test
    public void date_test_string_with_wrong_sdf()throws Exception{
        //验证时间格式是否正确
        RegValidation reg = new RegValidation();
        String date = "2017-06-30 14:32:59";
        Assert.assertFalse(reg.readDate(date));
    }

    @Test
    public void date_test_string_with_invalid_sdf()throws Exception{
        //验证时间格式是否正确
        RegValidation reg = new RegValidation();
        String date = "2013/02/29 14:32:59";
        Assert.assertFalse(reg.readDate(date));
    }

    @Test
    public void date_test_string_with_invalid_sdfnumber()throws Exception{
        //验证时间格式是否正确
        RegValidation reg = new RegValidation();
        String date = "2013/02/29 14:32:60";
        Assert.assertFalse(reg.readDate(date));
    }

    @Test
    public void date_test_string_with_valid_sdf()throws Exception{
        //验证时间格式是否正确
        RegValidation reg = new RegValidation();
        String date = "2016/02/29 14:32:59";
        Assert.assertTrue(reg.readDate(date));
    }

    @Test
    public void pos_test_string_with_invalid_length(){
        //坐标格式是否正确
        RegValidation reg = new RegValidation();
        String pos = "cat1";
        Assert.assertFalse(reg.readPos(pos));
    }

    @Test
    public void pos_test_string_with_invalid_string(){
        //坐标格式是否正确
        RegValidation reg = new RegValidation();
        String pos = "cat1 10 a";
        String pos2 = "cat1 10 a 3 -1";
        Assert.assertFalse(reg.readPos(pos));
        Assert.assertFalse(reg.readPos(pos2));
    }

    @Test
    public void pos_test_string_with_invalid_num() {
        //坐标格式是否正确
        RegValidation reg = new RegValidation();
        String pos = "cat1 10 3.1";
        Assert.assertFalse(reg.readPos(pos));
    }
    @Test
    public void pos_test_string_with_valid_format_one() {
        //坐标格式是否正确
        RegValidation reg = new RegValidation();
        String pos = "cat1 10 3";
        Assert.assertTrue(reg.readPos(pos));
    }

    @Test
    public void pos_test_string_with_valid_format_two() {
        //坐标格式是否正确
        RegValidation reg = new RegValidation();
        String pos = "cat1 10 3 5 2";
        Assert.assertTrue(reg.readPos(pos));
    }

    @Test
    public void list_test_string_with_invalid_id()throws Exception{
        //测试
        RegValidation reg = new RegValidation();
        ArrayList<String> list = new ArrayList<String>();
        list.add("e4e87cb2-8e9a-4749-abb6-26c59344dfe e\n2016/09/02 22:30:46\ncat1 10 9");
        Assert.assertFalse(reg.readList(list));
    }
    @Test
    public void list_test_string_with_invalid_date()throws Exception{
        //测试
        RegValidation reg = new RegValidation();
        ArrayList<String> list = new ArrayList<String>();
        list.add("e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:30:60\ncat1 10 9");
        Assert.assertFalse(reg.readList(list));
    }
    @Test
    public void list_test_string_with_invalid_pos()throws Exception{
        //模块测试
        RegValidation reg = new RegValidation();
        ArrayList<String> list = new ArrayList<String>();
        list.add("e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/29 22:30:60\ncat1 10");
        Assert.assertFalse(reg.readList(list));
    }

    @Test
    public void list_test_string_with_invalid_all()throws Exception{
        //测试
        RegValidation reg = new RegValidation();
        ArrayList<String> list = new ArrayList<String>();
        list.add("e4e87cb2-8e9a-4749-abb6-26c59344dfe e\n2015/02/29 22:30:60\ncat1 10 w");
        Assert.assertFalse(reg.readList(list));
    }
    @Test
    public void list_test_string_with_valid_all()throws Exception{
        //模块
        RegValidation reg = new RegValidation();
        ArrayList<String> list = new ArrayList<String>();
        list.add("e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:30:59\ncat1 10 1\ncat1 11 2");
        list.add("e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:30:59\ncat1 10 1\ncat2 11 2");
        Assert.assertTrue(reg.readList(list));
    }
//
    @Test
    public void historyData_test_string_with_invalid_line()throws Exception{
        //无空行隔开的数据
        RegValidation reg = new RegValidation();
        String historyData = "e44dfee\n2015/02/28 22:30:59\ncat2 11 2";
        ArrayList<String> res = reg.readHistoryData(historyData);

        Assert.assertEquals(res.size(),1);
    }
//
    @Test
    public void historyData_test_string_with_valid_all()throws Exception{
        //空行隔开的合法数据
        RegValidation reg = new RegValidation();
        String historyData = "e4e8\n\ncat1 11 2\n\ne4e87cb2-8e9a-4749-abb6\n\ncat1 10 1\ncat2 11 2";
        ArrayList<String> res = reg.readHistoryData(historyData);
        System.out.println(res.size());
        Assert.assertEquals(res.size(),2);
    }

    @Test
    public void list_test_with_historyData_no_blank_invaliddata()throws Exception{
        RegValidation reg = new RegValidation();
        //数据之间没有空行隔开,缺少部分数据,不是完整数据格式:id-时刻-坐标
        String historyData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:30:59\ncat1 10 1\ncat1 11 2\ne4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:31:19\n";
        ArrayList<String> list = reg.readHistoryData(historyData);
        Assert.assertFalse(reg.readList(list));
    }

    @Test
    public void string_test_without_historyData()throws Exception{
        RegValidation reg = new RegValidation();
        //空数据
        String historyData = "";
        ArrayList<String> list = reg.readHistoryData(historyData);
        Assert.assertFalse(reg.readList(list));
    }
    @Test
    public void string_test_with_historyData_blank()throws Exception{
        RegValidation reg = new RegValidation();
        //空数据
        String historyData = "  ";
        ArrayList<String> list = reg.readHistoryData(historyData);
        Assert.assertFalse(reg.readList(list));
    }
    @Test
    public void string_test_with_historyData_valid_no_blank_line()throws Exception{
        RegValidation reg = new RegValidation();
        //数据之间没有空行隔开,数据格式完整:id-时刻-坐标
        String historyData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:30:59\ncat1 10 1\ncat1 11 2\ne4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:31:19\ncat1 10 1\ncat2 11 2";
        ArrayList<String> list = reg.readHistoryData(historyData);
        Assert.assertTrue(reg.readList(list));
    }

    @Test
    public void string_test_with_historyData_blank_line()throws Exception{
        RegValidation reg = new RegValidation();
        //数据之间有空行隔开,数据格式完整:id-时刻-坐标
        String historyData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:30:59\ncat1 10 1\ncat1 11 2\n\ne4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:31:19\ncat1 10 1\ncat2 11 2";
        ArrayList<String> list = reg.readHistoryData(historyData);
        Assert.assertTrue(reg.readList(list));
    }

    @Test
    public void time_repeat_test_with_historyData()throws Exception{
        RegValidation reg = new RegValidation();
        //数据格式:id-时刻-坐标,验证时间是否有重复值
        ArrayList<String> list = new ArrayList<String>();
        list.add("e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:30:59\ncat1 10 1\ncat1 11 2");
        list.add("e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:30:59\ncat1 10 1\ncat2 11 2");
        Assert.assertFalse(reg.readtimeList(list));
    }

}
