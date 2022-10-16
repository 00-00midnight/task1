package packag.e;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;



public class Main {
    public static void main(String[] args) throws IllegalAccessException, IOException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Test test = new Test();
        TestBig testBig = new TestBig();
        CSVParser<Test> parser = new CSVParser<>(test);
        CSVParser<TestBig> parserBig = new CSVParser<>(testBig);

        File file = new File("src/packag/e/cities.csv");
//        File fileBig = new File("src/packag/e/5m Sales Records.csv");

        ArrayList<Test> result = parser.Parse(file);
        for(int i = 0; i < result.size(); i++)
            System.out.println(result.get(i).LatD);

//        ArrayList<TestBig> resultBig = parserBig.Parse(fileBig);
//        for(int i = 0; i < resultBig.size(); i++)
//            System.out.println(resultBig.get(i).country);

    }
}
