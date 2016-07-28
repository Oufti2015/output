package sst.common.file.replaceFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import sst.common.file.output.OutputFile;

public class ReplaceFile {
    private HashMap<String, String> dictionnary = new HashMap<>();

    public void add(String key, String value) {
        dictionnary.put(key, value);
    }

    public void run(String inputFileName, String outputFileName) throws FileNotFoundException, IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(new File(inputFileName)))) {
            try (OutputFile output = new OutputFile(outputFileName)) {
                String line = null;
                while (null != (line = input.readLine())) {
                    Collection<String> keys = dictionnary.keySet();
                    for (String key : keys) {
                        if (line.contains(key)) {
                            line = line.replaceAll(key, dictionnary.get(key));
                        }
                    }
                    output.println(line);
                }
            }
        }
    }
}
