package com.example.demo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtilImpl implements FileUtil {

	@Override
	public String readFile(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
		String line;
		StringBuilder stringBuilder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line).append(System.lineSeparator());
		}
		System.out.println(stringBuilder);
		return stringBuilder.toString().trim();
	}

}
