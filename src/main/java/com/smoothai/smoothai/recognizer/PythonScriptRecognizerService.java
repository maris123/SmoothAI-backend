package com.smoothai.smoothai.recognizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PythonScriptRecognizerService implements ImageRecognizerService {

	private final static Logger log = LoggerFactory.getLogger(PythonScriptRecognizerService.class);

	@Value("${smoothai.darknet.path}")
	private Path darknetPath;

	@Override
	public List<String> getIngredients(Path filePath) {
		List<String> ingredients = RecognizeResultParser.ingredients(runPythonScript(filePath));
		log.info("Ingredients: {}", ingredients.toString());
		return ingredients;
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
			log.error("Error while running darknet process. {}.", e.getMessage());
		}

		log.info("Finished process.");
		return recognizeResult;
	}
}
