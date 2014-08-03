package net.earthcoder.jmlm.domain;

import java.io.IOException;

public final class BinaryPlanTest4 {

    private static final String PATH = "C:\\Users\\Wei\\Project\\Huazhi\\src\\JMLM\\src\\main\\webapp\\test.html";
    private static final String START = "<a href=\"#\">";
    private static final String[] OVERS = {"</a>", "<ul>", "<li>", "</ul>", "</li>", "<div class=\"tree\">", "</div>",
            "<head>", "</head>", "<body style=\"width: 16000px;\">", "</body>", "<html>", "</html>", "<!DOCTYPE html>", "\n",
            "<title>test.html</title>", "<link rel=\"stylesheet\" type=\"text/css\" href=\"test.css\">", "\t"};
    private static final String FLAG = "|";
    private static final String FLAG_PATTERN = "\\|";
    private static final String BLANK = "";

    BinaryTree plan = BinaryTree.getNewTree();
    private static final String[] names;
    
    static {
        String str = null;
        try {
            str = FileUtils.readFile(PATH);
        } catch (IOException ioError) {
            ioError.printStackTrace();
        }
        String replacedString = str.replace(START, FLAG);
        for (String over : OVERS) {
            replacedString = replacedString.replace(over, BLANK);
        } 
        names = replacedString.split(FLAG_PATTERN);
    }
    
    private int getID(String find) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(find)) {
                return i;
            }
        }
        return 0;
    }
    
    private void add(String name, String name2, String flag) {
        plan.addNode(new People(getID(name)), getID(name2), getID(name2));
    }
    
    private void addOne(String name, String name2, String flag) {
        add(name, name2, flag);
        add(name + "_A", name, "LEFT");
        add(name + "_B", name, "RIGHT");
    }

    public static void main(String[] args) {
        BinaryPlanTest4 instance = new BinaryPlanTest4();

        instance.init();
        
        
        instance.addOne("王凤枝（1）(18)", "于景威（20）", "LEFT");
        
        instance.addOne("齐春花（1）(27)", "王凤枝（1）(18)_A", "LEFT");
        instance.addOne("齐春花（2）(28)", "齐春花（1）(27)_A", "LEFT");
        instance.addOne("齐春花（6）(142)", "齐春花（2）(28)_A", "LEFT");
        instance.addOne("齐春花（7）(185)", "齐春花（2）(28)_B", "LEFT");
        instance.addOne("斯日古楞（1）(88)", "齐春花（2）(28)_B", "RIGHT");
        instance.addOne("阿拉坦其其格（1）(194)", "齐春花（6）(142)_A", "LEFT");
        instance.addOne("齐春花（3）(29)", "齐春花（1）(27)_A", "RIGHT");
        instance.addOne("齐春花（4）(140)", "齐春花（1）(27)_B", "LEFT");
        instance.addOne("齐春花（5）(141)", "齐春花（1）(27)_B", "RIGHT");
        instance.addOne("王凤枝（2）(19)", "王凤枝（1）(18)_B", "LEFT");
        instance.addOne("李云英（1）(6)", "于景威（20）", "RIGHT");
        instance.addOne("李云英（2）(7)", "李云英（1）(6)_A", "RIGHT");
        instance.addOne("冯彩霞（1）(21)", "李云英（1）(6)_B", "RIGHT");
        instance.addOne("包红英（1）(212)", "齐春花（7）(185)_A", "LEFT");
        instance.addOne("锡林托雅（1）(235)", "包红英（1）(212)_A", "LEFT");
        instance.addOne("乃日木德勒（1）(168)", "齐春花（7）(185)_B", "LEFT");
        instance.addOne("森德扎步（1）(214)", "乃日木德勒（1）(168)_A", "LEFT");
        instance.addOne("哈斯花（1）(208)", "森德扎步（1）(214)_A", "LEFT");
        instance.addOne("乃日木德勒（2）(170)", "乃日木德勒（1）(168)_B", "LEFT");
        instance.addOne("斯日古楞（2）(173)", "斯日古楞（1）(88)_A", "LEFT");
        instance.addOne("文达日刚（1）(100)", "斯日古楞（2）(173)_A", "LEFT");
        instance.addOne("文达日刚（2）(143)", "文达日刚（1）(100)_A", "LEFT");
        instance.addOne("文达日刚（4）(238)", "文达日刚（2）(143)_A", "LEFT");
        instance.addOne("文达日刚（3）(195)", "文达日刚（1）(100)_B", "LEFT");
        instance.addOne("乌日汗（1）(216)", "文达日刚（3）(195)_A", "LEFT");
        instance.addOne("贺林其其格（1）(174)", "斯日古楞（2）(173)_B", "LEFT");
        instance.addOne("乌云其木格（1）(191)", "贺林其其格（1）(174)_A", "LEFT");
        instance.addOne("斯日古楞（4）(241)", "乌云其木格（1）(191)_A", "LEFT");
        instance.addOne("于凤荣（1）(203)", "贺林其其格（1）(174)_B", "LEFT");
        instance.addOne("王海霞（1）(225)", "于凤荣（1）(203)_A", "LEFT");
        instance.addOne("于凤荣（2）(237)", "于凤荣（1）(203)_B", "LEFT");
        instance.addOne("斯日古楞（3）(217)", "斯日古楞（1）(88)_B", "LEFT");
        instance.addOne("其木格（1）阿旗(167)", "斯日古楞（3）(217)_A", "LEFT");
        instance.addOne("其木格（2）阿旗(196)", "其木格（1）阿旗(167)_A", "LEFT");
        instance.addOne("傲登格日勒（1）(234)", "其木格（1）阿旗(167)_B", "LEFT");
        instance.addOne("齐春花（8）(192)", "齐春花（3）(29)_A", "LEFT");
        instance.addOne("萨仁图雅（1）(221)", "齐春花（8）(192)_A", "LEFT");
        instance.addOne("宝力德其其格（1）(171)", "齐春花（8）(192)_B", "LEFT");
        instance.addOne("宝力德其其格（2）阿旗(198)", "宝力德其其格（1）(171)_A", "LEFT");
        instance.addOne("娜仁格日乐（1）(89)", "宝力德其其格（2）阿旗(198)_A", "LEFT");
        instance.addOne("萨其尔（1）(193)", "齐春花（3）(29)_B", "LEFT");
        instance.addOne("萨其尔（2）(239)", "萨其尔（1）(193)_A", "LEFT");
        instance.addOne("萨其尔（3）(240)", "萨其尔（1）(193)_B", "LEFT");
        instance.addOne("萨仁花（1）(176)", "萨其尔（2）(239)_A", "LEFT");
        instance.addOne("韩乌仁沙娜（1）(30)", "齐春花（4）(140)_B", "LEFT");
        instance.addOne("韩乌仁沙娜（2）(94)", "韩乌仁沙娜（1）(30)_A", "LEFT");
        instance.addOne("荆秀平（1）(34)", "韩乌仁沙娜（2）(94)_A", "LEFT");
        instance.addOne("荆秀平（2）(35)", "荆秀平（1）(34)_A", "LEFT");
        instance.addOne("荆秀平（4）(99)", "荆秀平（2）(35)_A", "LEFT");
        instance.addOne("李翠香（1）(37)", "荆秀平（4）(99)_A", "LEFT");
        instance.addOne("李翠香（2）(38)", "李翠香（1）(37)_A", "LEFT");
        instance.addOne("李翠香（4）(107)", "李翠香（2）(38)_A", "LEFT");
        instance.addOne("段军（1）(164)", "李翠香（4）(107)_A", "LEFT");
        instance.addOne("范柏英（1）(186)", "段军（1）(164)_A", "LEFT");
        instance.addOne("云龙飞（1）(180)", "李翠香（2）(38)_B", "LEFT");
        instance.addOne("李小燕（1）(236)", "云龙飞（1）(180)_A", "LEFT");
        instance.addOne("李翠香（3）(39)", "李翠香（1）(37)_B", "LEFT");
        instance.addOne("李翠香（5）(131)", "李翠香（3）(39)_A", "LEFT");
        instance.addOne("曹木尔其力（1）(228)", "李翠香（5）(131)_A", "LEFT");
        instance.addOne("荆秀平（3）(36)", "荆秀平（1）(34)_B", "LEFT");
        instance.addOne("荆秀平（5）(108)", "荆秀平（3）(36)_A", "LEFT");
        instance.addOne("荆秀平（6）(187)", "荆秀平（3）(36)_B", "LEFT");
        instance.addOne("韩乌仁沙娜（3）(109)", "韩乌仁沙娜（1）(30)_B", "LEFT");
        instance.addOne("其木格（1）二连(31)", "韩乌仁沙娜（3）(109)_A", "LEFT");
        instance.addOne("其木格（2）二连(32)", "其木格（1）二连(31)_A", "LEFT");
        instance.addOne("韩丽（1）(134)", "其木格（2）二连(32)_A", "LEFT");
        instance.addOne("吴奎（1）(33)", "其木格（1）二连(31)_B", "LEFT");
        instance.addOne("苏义拉其其格（1）(46)", "韩乌仁沙娜（3）(109)_B", "LEFT");
        instance.addOne("苏义拉其其格（2）(47)", "苏义拉其其格（1）(46)_A", "LEFT");
        instance.addOne("苏义拉其其格（3）(48)", "苏义拉其其格（2）(47)_A", "LEFT");
        instance.addOne("托娅（1）(205)", "苏义拉其其格（1）(46)_B", "LEFT");
        instance.addOne("齐春花（9）(220)", "齐春花（5）(141)_A", "LEFT");
        instance.addOne("王凤枝（3）(20)", "王凤枝（2）(19)_A", "LEFT");
        instance.addOne("其其格（1）二连(188)", "王凤枝（3）(20)_A", "LEFT");
        instance.addOne("王小艳（1）(222)", "王凤枝（3）(20)_B", "LEFT");
        instance.addOne("王凤枝（4）(106)", "王凤枝（2）(19)_B", "LEFT");
        instance.addOne("王君毅（1）(149)", "王凤枝（4）(106)_A", "LEFT");
        instance.addOne("张婧（1）(73)", "王君毅（1）(149)_A", "LEFT");
        instance.addOne("张婧（2）(74)", "张婧（1）(73)_A", "LEFT");
        instance.addOne("张婧（3）(75)", "张婧（2）(74)_A", "LEFT");
        instance.addOne("梁燕（1）(145)", "张婧（3）(75)_A", "LEFT");
        instance.addOne("李霞（1）(91)", "张婧（2）(74)_B", "LEFT");
        instance.addOne("钟丽霞（1）(129)", "李霞（1）(91)_A", "LEFT");
        instance.addOne("张雅丽（1）(90)", "张婧（1）(73)_B", "LEFT");
        instance.addOne("孙吉仙（1）(162)", "张雅丽（1）(90)_A", "LEFT");
        instance.addOne("王巧丽（1）(128)", "孙吉仙（1）(162)_A", "LEFT");
        instance.addOne("王二桃（1）(130)", "王巧丽（1）(128)_A", "LEFT");
        instance.addOne("王凤桃（1）(146)", "王凤枝（4）(106)_B", "LEFT");
        instance.addOne("梁润梅（1）(204)", "李云英（2）(7)_A", "LEFT");
        instance.addOne("庞玉娥（1）(206)", "梁润梅（1）(204)_A", "LEFT");
        instance.addOne("庞玉娥（2）(223)", "庞玉娥（1）(206)_A", "LEFT");
        instance.addOne("张玉文（1）(15)", "庞玉娥（2）(223)_A", "LEFT");
        instance.addOne("张玉文（2）(16)", "张玉文（1）(15)_A", "LEFT");
        instance.addOne("郭燕（1）(24)", "张玉文（2）(16)_A", "LEFT");
        instance.addOne("郭燕（2）(25)", "郭燕（1）(24)_A", "LEFT");
        instance.addOne("刘云侠（1）(40)", "郭燕（2）(25)_A", "LEFT");
        instance.addOne("刘云侠（2）(41)", "刘云侠（1）(40)_A", "LEFT");
        instance.addOne("杨立强（1）(70)", "刘云侠（2）(41)_A", "LEFT");
        instance.addOne("杨立强（2）(71)", "杨立强（1）(70)_A", "LEFT");
        instance.addOne("杨立强（3）(72)", "刘云侠（2）(41)_B", "LEFT");

        instance.plan.printNode();
        instance.plan.printBill();
    }
    
    private void init() {
        plan.addNode(new People(getID(names[1])), null, null);
        for (int i = 2; i < 21; i++) {
            add(names[i], names[i - 1], "LEFT");
        }
    }
}
