
git:   https://github.com/orangelily/Animal.git

运行过程:
     CatAnalysis中的main方法
     读取historyData.txt
     控制台读取需要查找的id


# 根据需求先划分小任务:
    1.格式验证:id、时刻、动物id 坐标x y (变化值x' y')

    2.假定id全局唯一,省略测试过程
      数据冲突:时刻是否冲突、对应时刻的坐标是否冲突

    3.以上问题都不存在时,实现根据id返回对应动物的Id及该动物的坐标(新建Animal类,实现按动物Id升序排列)


1.格式验证:id、时刻、动物id 坐标x y
    1.1 id:非空字符串、不包含空格
        新建RegValidation类,实现readId方法,正则表达式验证id是否为正确的格式,
        新建RegValidationTest类,实现针对id的多个测试方法;
        测试用例包含空字符串、包含空格的字符串等无效等价类;
        非空字符串的有效等价类;

    1.2 时刻:yyyy/MM/dd HH:mm:ss
        RegValidation类中添加readDate方法,验证当前时刻是否为正确的时间格式,
        RegValidationTest类,实现针对时刻的多个测试方法;
        测试用例包含字符串、非yyyy/MM/dd HH:mm:ss时间格式的无效等价类及yyyy/MM/dd HH:mm:ss格式的不正确时间测试用例,如2013/02/29  22:31:19
        yyyy/MM/dd HH:mm:ss格式的有效等价类;

    1.3 动物Id 坐标
        第一种格式:{ Id} {x } {y }
        第二种格式:{ Id} {上一时刻x } {上一时刻y } {x的变化量 } {y的变化量}

        RegValidation类中添加readPos方法,验证动物Id及坐标的格式是否正确
        RegValidationTest类,实现针对坐标的多个测试方法;
        测试用例包含不正确的格式、正确格式的字符串、非整数等无效等价类;
        及两种格式正确的有效等价类;

    1.4 结合上述三种方法,将"id\n时刻\n坐标及变化值"存为一个小模块list,判断格式是否有效
        RegValidation类中添加readList方法,验证每个数据模块是否正确
        RegValidationTest类,实现针对list集合的多个测试方法;
        测试用例:
          无效类:时间无效、坐标无效、时间+坐标无效
                "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:30:60\ncat1 10 9"
                "e4e87cb2-8e9a-4749-abb6-26c59344dfe e\n2015/02/28 22:30:60\ncat1 10 w"
                "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/29 22:30:60\ncat1 10"
          有效类:
                "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2015/02/28 22:30:59\ncat1 10 1\ncat1 11 2"


    1.5 读取字符串historyData,转化为上一步骤中的list,判断list的数据内容转化是否正确
        RegValidation类中添加public ArrayList<String> readHistoryData(String historyData);
        RegValidationTest类,实现针对字符串historyData转化的多个测试方法,验证数目是否一致,内容是否正确
        如historyData_test_string_with_invalid_line中list.size()==1
            historyData_test_string_with_valid_all中list.size()==2

    1.6 获取字符串historyData转化成功之后的list,验证数据时刻的有效性,进行集成测试
        RegValidation类中添加readtimeList;验证historyData中的时刻是否重复(默认都是递增的时刻)
        RegValidationTest类中添加多个测试方法



2. 数据合法性验证
    * 通过格式验证的数据才能执行该过程

    2.1 对应时刻的坐标是否合理
    PosValidation下新建readposList,读取分割好的list,验证坐标数据是否有冲突
    PosValidationTest下新建多个测试方法,测试用例包含无冲突的坐标数据及有冲突的坐标数据

3. 新建Animal类,包含animalId,坐标信息
    实现按animalId递增的排序方法

    完成系统测试

