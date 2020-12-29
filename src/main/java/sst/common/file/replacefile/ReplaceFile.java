package sst.common.file.replacefile;

import sst.common.file.output.OutputFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class ReplaceFile {
    private final HashMap<String, String> dictionnary = new HashMap<>();

    public void add(String key, String value) {
        dictionnary.put(key, value);
    }

    public void run(String inputFileName, String outputFileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(inputFileName))) {
            try (OutputFile output = new OutputFile(outputFileName)) {
                String line;
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
