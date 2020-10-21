package cn.edu.zzu.tasks._1_;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class array_chars_vector_hashtable {
    public static void main(String[] args) throws FileNotFoundException {
        computeWords();
    }

    public static void computeWords() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("D:\\words.txt"));
        Map<String, Integer> map = new HashMap();
        while(scanner.hasNext()){
            String word = scanner.next().replaceAll("[;,.*]","");
            map.put(word, map.getOrDefault(word, 0)+1);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList();
        list.addAll(map.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        });
        for(Map.Entry<String, Integer> e : list){
            System.out.println(e);
        }
    }
}
