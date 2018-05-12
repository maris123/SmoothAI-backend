package com.smoothai.smoothai.recognizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PythonScriptRecognizerService implements ImageRecognizerService {

	private Logger log = Logger.getLogger(PythonScriptRecognizerService.class.toString());

	@Value("${smoothai.darknet.path}")
	private Path darknetPath;

	@Override
	public List<String> getIngredients(Path filePath) {
		return RecognizeResultParser.ingredients(runPythonScript(filePath));
	}

	private List<String> runPythonScript(Path filePath) {
		List<String> recognizeResult = new ArrayList<>();

		try {
			log.info("Started darknet process");
			ProcessBuilder processBuilder = new ProcessBuilder("./darknet", "detect", "cfg/yolov3.cfg",
					"yolov3.weights", filePath.toString());
			processBuilder.directory(new File(darknetPath.toString()));
			Process process = processBuilder.start();

			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String readLine;

			while ((readLine = in.readLine()) != null) {
				recognizeResult.add(readLine);
			}
		} catch (IOException e) {

		}

		log.info("Finished process.");
		return recognizeResult;
	}
}
