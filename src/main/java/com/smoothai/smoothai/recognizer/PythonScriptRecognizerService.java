package com.smoothai.smoothai.recognizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class PythonScriptRecognizerService implements ImageRecognizerService {

	@Value("smoothai.recognition.script.path")
	private Path pythonScriptPath;

	@Value("smoothai.darknet.app")
	private Path darknetApp;

	@Override
	public List<String> getFruits(Path filePath) {
		String output = runPythonScript(filePath);
		return null;
	}

	private String runPythonScript(Path filePath) {
		StringBuilder output = new StringBuilder();

		try {
			ProcessBuilder processBuilder = new ProcessBuilder(darknetApp.toString(), "detect", "cfg/yolov3.cfg",
					"yolov3.weights", filePath.toString());
			Process process = processBuilder.start();

			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String readLine;

			while ((readLine = in.readLine()) != null) {
				output.append(readLine + System.lineSeparator());
			}
		} catch (IOException e) {
			
		}

		return output.toString();
	}
}
