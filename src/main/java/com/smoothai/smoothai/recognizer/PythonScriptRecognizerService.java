package com.smoothai.smoothai.recognizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PythonScriptRecognizerService implements ImageRecognizerService {

	@Value("${smoothai.darknet.path}")
	private Path darknetApp;

	@Override
	public List<String> getFruits(Path filePath) {
		String output = runPythonScript(filePath);
		return null;
	}

	private String runPythonScript(Path filePath) {
		StringBuilder output = new StringBuilder();

		try {
			ProcessBuilder processBuilder = new ProcessBuilder("./darknet", "detect", "cfg/yolov3.cfg",
					"yolov3.weights", filePath.toString());
			processBuilder.directory(new File(darknetApp.toString()));
			Process process = processBuilder.start();

			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String readLine;

			List<String> recognizeResult = new ArrayList<>();
			while ((readLine = in.readLine()) != null) {
				recognizeResult.add(readLine);
			}
		} catch (IOException e) {
			
		}

		return output.toString();
	}
}
