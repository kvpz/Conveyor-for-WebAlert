package webalertmisc;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

public class Utility {
    public static File getConfigFile(String filePath) {
        File cfile = new File(filePath);
        if(!cfile.exists()) {
            System.out.println("'" + filePath + "'" + " does not identify an existing file.");
            System.exit(-1);
        }

        return cfile;
    }

    public static Map<?,?> jsonFileToMap(File configFile) {
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = new FileReader(configFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        Map<?,?> map = gson.fromJson(reader, Map.class);


        return map;
    }
}
