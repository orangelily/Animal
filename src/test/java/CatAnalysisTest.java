import org.junit.Test;

/**
 * Created by orange on 16/9/22.
 */
public class CatAnalysisTest {

    @Test
    public void snaptest_without_id()throws Exception{
        String historyData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:30:19\ncat1 10 1 2 3\n"+
                "e4e87cb2-8e9a-4749-abb6-ss\n2015/02/28 22:31:19\ncat1 12 4 2 3";
        String id = "e4e87cb2-8e9a-4749-abb6-26c59344df";
        CatAnalysis cat = new CatAnalysis();
        String output = cat.getSnapShot(historyData,id);
        System.out.println(output);
    }

    @Test
    public void getSnapTest()throws Exception{
        String historyData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:30:19\ncat1 10 1 2 3\n"+
                "e4e87cb2-8e9a-4749-abb6-ss\n2015/02/28 22:31:19\ncat1 12 4 2 3";
        String id = "e4e87cb2-8e9a-4749-abb6-26c59344dfee";
        CatAnalysis cat = new CatAnalysis();
        String output = cat.getSnapShot(historyData,id);
        System.out.println(output);
    }
}
