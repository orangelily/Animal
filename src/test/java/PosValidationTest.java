import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by orange on 16/9/22.
 */
public class PosValidationTest {
    @Test
    public void pos_test_with_list_conflict()throws Exception{
        ArrayList<String> list = new ArrayList<String>();
        list.add("e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:30:59\ncat1 10 1\ncat1 11 2");
        PosValidation pos = new PosValidation();
        Assert.assertNotNull(pos.readposList(list));
        //返回冲突的id
        Assert.assertEquals(pos.readposList(list),"e4e87cb2-8e9a-4749-abb6-26c59344dfee");
    }

    @Test
    public void pos_test_without_list_conflict()throws Exception{
        ArrayList<String> list = new ArrayList<String>();
        list.add("e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:31:19\ncat1 10 1 2 3");
        list.add("e4e87cb2-8e9a-4749-abb6-ss\n2015/02/28 22:31:19\ncat1 12 4 2 3");
        PosValidation pos = new PosValidation();
        //无冲突,返回空值
        Assert.assertNull(pos.readposList(list));
    }

}
