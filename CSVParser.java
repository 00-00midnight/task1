package packag.e;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVParser<T> {
    Class t;

    CSVParser(T clazz) {
        t = clazz.getClass();
    }

    public ArrayList<T> Parse(File file) throws IOException, InstantiationException, IllegalAccessException {
        System.out.println("Starting parsing...");

        ArrayList<T> result = new ArrayList<>();
        InputStream stream = new FileInputStream(file);
        Scanner sc = new Scanner(stream);
        String[] currentValues;

        sc.nextLine();
        while (sc.hasNextLine()){
            T currentObj = (T) t.newInstance();
            Field[] currentObjFields = currentObj.getClass().getFields();
            currentValues = sc.nextLine().split(",");

            Type type;
            for (int i = 0; i < currentObjFields.length; i++){
                type = currentObjFields[i].getType();

                if (type.equals(Integer.TYPE)){
                    currentObjFields[i].setInt(currentObj, Integer.parseInt(currentValues[i].replace(" ", "")));
                }else if (type.equals(Float.TYPE)){
                    currentObjFields[i].setFloat(currentObj, Float.parseFloat(currentValues[i].replace(" ", "")));
                }else if (type.equals(Double.TYPE)) {
                    currentObjFields[i].setDouble(currentObj, Double.parseDouble(currentValues[i].replace(" ", "")));
                }else if (type.equals(Boolean.TYPE)) {
                    currentObjFields[i].setBoolean(currentObj, Boolean.parseBoolean(currentValues[i].replace(" ", "")));
                }else if (type.equals(Character.TYPE)) {
                    currentObjFields[i].setChar(currentObj, currentValues[i].charAt(0));
                }else
                    currentObjFields[i].set(currentObj, currentValues[i]);
            }
            for(int i = 0; i < currentObjFields.length; i++)
                System.out.print(currentObjFields[i].get(currentObj) + " ");
            System.out.println();
            result.add(currentObj);
            System.gc();
        }
        System.out.println("Parsing done!");
        return result;
    }
}



//            "^[0-9]*[.,][0-9]+$"