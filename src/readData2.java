import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.trees.international.pennchinese.ChineseGrammaticalStructure;
import sun.font.TrueTypeFont;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;

public class readData {


    public static String parserLine(String str){
        String modelpath = "edu/stanford/nlp/models/lexparser/xinhuaFactoredSegmenting.ser.gz";
        LexicalizedParser lp = LexicalizedParser.loadModel(modelpath);
        Tree t = lp.parse(str);
        t.pennPrint();
        ChineseGrammaticalStructure gs = new ChineseGrammaticalStructure(t);//中文语法结构
        Collection<TypedDependency> tdl = gs.typedDependenciesCCprocessed();//依存关系分析
        return(tdl.toString().replace(" ",""));

    }

    public static String parserLine2(String str){
        String modelpath = "edu/stanford/nlp/models/lexparser/xinhuaFactoredSegmenting.ser.gz";
        LexicalizedParser lp = LexicalizedParser.loadModel(modelpath);
        Tree t = lp.parse(str);
        t.pennPrint();
        ChineseGrammaticalStructure gs = new ChineseGrammaticalStructure(t);//中文语法结构
        Collection<TypedDependency> tdl = gs.typedDependenciesCCprocessed();//依存关系分析
        return(tdl.toString().replace(" ",""));
    }


    public static void main(String args[]) {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            /* 读入TXT文件 */
            String pathname = "data/电子商务.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename),"GBK"); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";


            File writename = new File("data/电子商务parser.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
//            out.write("我会写入文件啦\r\n"); // \r\n即为换行



            while (true) {
                line = br.readLine();// 一次读入一行数据
                if (line==null){
                    break;
                }
//                System.out.println(line);

                String[] splitLine=line.split(" ");

//                System.out.println(Arrays.toString(splitLine));

                String tmpStr=splitLine[2];

                splitLine[2]=parserLine(tmpStr);
//                System.out.println(Arrays.toString(splitLine));
//                out.write(Arrays.toString(splitLine));
                out.write((splitLine[0]+" "+splitLine[1]+" "+splitLine[2]));
                System.out.println((splitLine[0]+" "+splitLine[1]+" "+splitLine[2]));
                out.write("\n");

            }

            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件

            /* 写入Txt文件 */
 /*           File writename = new File("output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write("我会写入文件啦\r\n"); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    解析句子


}
