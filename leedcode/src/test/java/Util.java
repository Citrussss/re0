import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Util {

    private static final String URL = "/Users/bangbang/Desktop/会体得心习学人好育";

    public static void main(String[] args) throws IOException {
        FileReversion(URL);
    }

    private static void FileReversion(String path) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            br = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<>();
            while (true) {
                String s = br.readLine();
                if (s != null) {
                    lines.add(s);
                } else {
                    break;
                }
            }
            bw = new BufferedWriter(new FileWriter(file.getAbsolutePath() + System.currentTimeMillis()));
            for (int i = lines.size() - 1; i >= 0; i--) {
                char[] chars = lines.get(i).toCharArray();
                for (int j = chars.length - 1; j >= 0; j--) {
                    bw.write(chars[j]);
                }
                bw.newLine();
            }
            bw.flush();
        } finally {
            if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.close();
            }
        }
    }


   /* public void ff(List<Integer> list) {
        HashMap<Integer, Integer> minMap = new HashMap<>();
        HashMap<Integer, Integer> maxMap = new HashMap<>();
        int min = 0, max = 0;
        int index = 0;
        for (Integer integer : list) {
            if (max < integer) max = integer;
            maxMap.put(index, max);
        }
        for (int i = list.size() - 1; i > 0; i--) {
            if (min > list.get(i)) max = list.get(i);
            minMap.put(index, min);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Integer integer = list.get(i);
            if (integer>minMap.get(i)&&integer<maxMap.get(i))
        }
    }*/

}

