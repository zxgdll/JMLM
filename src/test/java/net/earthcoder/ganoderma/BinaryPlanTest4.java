package net.earthcoder.ganoderma;

import java.io.*;
import java.util.Calendar;

import net.earthcoder.ganoderma.man.People;
import net.earthcoder.ganoderma.struct.BinaryTree;
import net.earthcoder.ganoderma.struct.Tree;

public final class BinaryPlanTest4 {

    private static final String PATH = "C:\\Users\\Wei\\Project\\Huazhi\\src\\site2\\src\\main\\webapp\\test.html";
    private static final String START = "<a href=\"#\">";
    private static final String[] OVERS = {"</a>", "<ul>", "<li>", "</ul>", "</li>", "<div class=\"tree\">", "</div>",
            "<head>", "</head>", "<body style=\"width: 16000px;\">", "</body>", "<html>", "</html>", "<!DOCTYPE html>", "\n",
            "<title>test.html</title>", "<link rel=\"stylesheet\" type=\"text/css\" href=\"test.css\">", "\t"};
    private static final String FLAG = "|";
    private static final String FLAG_PATTERN = "\\|";
    private static final String BLANK = "";
    
    Tree<People> plan = new BinaryTree<People>();
    Calendar calendar = Calendar.getInstance();
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
        plan.addNode(new People(getID(name), name), calendar.getTime(), getID(name2), flag);
    }
    
    private void addOne(String name, String name2, String flag) {
        add(name, name2, flag);
        add(name + "_A", name, "L");
        add(name + "_B", name, "R");
    }

    public static void main(String[] args) {
        BinaryPlanTest4 instance = new BinaryPlanTest4();
        instance.init();
        
        
        instance.addOne("王凤枝（1）(18)", "于景威（20）", "L");
        
        instance.addOne("齐春花（1）(27)", "王凤枝（1）(18)_A", "L");
        instance.addOne("齐春花（2）(28)", "齐春花（1）(27)_A", "L");
        instance.addOne("齐春花（6）(142)", "齐春花（2）(28)_A", "L");
        instance.addOne("齐春花（7）(185)", "齐春花（2）(28)_B", "L");
        instance.addOne("斯日古楞（1）(88)", "齐春花（2）(28)_B", "R");
        instance.addOne("阿拉坦其其格（1）(194)", "齐春花（6）(142)_A", "L");
        instance.addOne("齐春花（3）(29)", "齐春花（1）(27)_A", "R");
        instance.addOne("齐春花（4）(140)", "齐春花（1）(27)_B", "L");
        instance.addOne("齐春花（5）(141)", "齐春花（1）(27)_B", "R");
        instance.addOne("王凤枝（2）(19)", "王凤枝（1）(18)_B", "L");
        instance.addOne("李云英（1）(6)", "于景威（20）", "R");
        instance.addOne("李云英（2）(7)", "李云英（1）(6)_A", "R");
        instance.addOne("冯彩霞（1）(21)", "李云英（1）(6)_B", "R");
        instance.addOne("包红英（1）(212)", "齐春花（7）(185)_A", "L");
        instance.addOne("锡林托雅（1）(235)", "包红英（1）(212)_A", "L");
        instance.addOne("乃日木德勒（1）(168)", "齐春花（7）(185)_B", "L");
        instance.addOne("森德扎步（1）(214)", "乃日木德勒（1）(168)_A", "L");
        instance.addOne("哈斯花（1）(208)", "森德扎步（1）(214)_A", "L");
        instance.addOne("乃日木德勒（2）(170)", "乃日木德勒（1）(168)_B", "L");
        instance.addOne("斯日古楞（2）(173)", "斯日古楞（1）(88)_A", "L");
        instance.addOne("文达日刚（1）(100)", "斯日古楞（2）(173)_A", "L");
        instance.addOne("文达日刚（2）(143)", "文达日刚（1）(100)_A", "L");
        instance.addOne("文达日刚（4）(238)", "文达日刚（2）(143)_A", "L");
        instance.addOne("文达日刚（3）(195)", "文达日刚（1）(100)_B", "L");
        instance.addOne("乌日汗（1）(216)", "文达日刚（3）(195)_A", "L");
        instance.addOne("贺林其其格（1）(174)", "斯日古楞（2）(173)_B", "L");
        instance.addOne("乌云其木格（1）(191)", "贺林其其格（1）(174)_A", "L");
        instance.addOne("斯日古楞（4）(241)", "乌云其木格（1）(191)_A", "L");
        instance.addOne("于凤荣（1）(203)", "贺林其其格（1）(174)_B", "L");
        instance.addOne("王海霞（1）(225)", "于凤荣（1）(203)_A", "L");
        instance.addOne("于凤荣（2）(237)", "于凤荣（1）(203)_B", "L");
        instance.addOne("斯日古楞（3）(217)", "斯日古楞（1）(88)_B", "L");
        instance.addOne("其木格（1）阿旗(167)", "斯日古楞（3）(217)_A", "L");
        instance.addOne("其木格（2）阿旗(196)", "其木格（1）阿旗(167)_A", "L");
        instance.addOne("傲登格日勒（1）(234)", "其木格（1）阿旗(167)_B", "L");
        instance.addOne("齐春花（8）(192)", "齐春花（3）(29)_A", "L");
        instance.addOne("萨仁图雅（1）(221)", "齐春花（8）(192)_A", "L");
        instance.addOne("宝力德其其格（1）(171)", "齐春花（8）(192)_B", "L");
        instance.addOne("宝力德其其格（2）阿旗(198)", "宝力德其其格（1）(171)_A", "L");
        instance.addOne("娜仁格日乐（1）(89)", "宝力德其其格（2）阿旗(198)_A", "L");
        
        
        instance.plan.print();
    }
    
    private void init() {
        plan.addNode(new People(getID(names[1]), names[1]), calendar.getTime(), null, null);
        for (int i = 2; i < 21; i++) {
            add(names[i], names[i - 1], "L");
        }
    }
}
