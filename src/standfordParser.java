import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.trees.international.pennchinese.ChineseGrammaticalStructure;

import java.util.Collection;

public class standfordParser {


    /**
     * Usage: {@code java ParserDemo [[model] textFile]}
     * e.g.: java ParserDemo edu/stanford/nlp/models/lexparser/chineseFactored.ser.gz data/chinese-onesent-utf8.txt
     *
     */

    public static void main(String[] args) {
        String modelpath = "edu/stanford/nlp/models/lexparser/xinhuaFactoredSegmenting.ser.gz";
        String str = "文本摘要通过基于Attention的seq-to-seq模型，对100字左右的短文本进行摘要输出。该摘要组件能根据段落大意，自动生成合适的摘要句子，生成的句子表达流畅，用词准确，重点清晰，能很好地概括";
        LexicalizedParser lp = LexicalizedParser.loadModel(modelpath);
        Tree t = lp.parse(str);
        t.pennPrint();
        ChineseGrammaticalStructure gs = new ChineseGrammaticalStructure(t);//中文语法结构
        Collection<TypedDependency> tdl = gs.typedDependenciesCCprocessed();//依存关系分析
        System.out.println(tdl.toString());

        //输出分词结果
 /*       String s = "";
        for (int i = 0; i < tdl.size(); i++) {
            TypedDependency td = (TypedDependency) tdl.toArray()[i];
            String age = td.dep().toString();
            s += age + "/";
            s += " ";
        }
        System.out.println(s);*/
    }

}
