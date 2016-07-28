package sst.common.file.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputFile implements AutoCloseable {
	private static final String TABULATION = "\t";
	private static final String LINE_SEPARATOR = "\n";

	private BufferedWriter output = null;

	public OutputFile(File file) throws IOException {
		output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
	}

	public OutputFile(String filename) throws IOException {
		output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
	}

	public void print(String line) throws IOException {
		output.write(line);
		output.flush();
	}

	public void println(int tab, String line) throws IOException {
		for (int i = 0; i < tab; i++, print(TABULATION))
			;
		print(line + LINE_SEPARATOR);
	}

	public void println(String line) throws IOException {
		print(line + LINE_SEPARATOR);
	}

	@Override
	public void close() throws IOException {
		output.close();
	}
}
