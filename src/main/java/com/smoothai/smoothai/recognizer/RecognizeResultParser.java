package com.smoothai.smoothai.recognizer;

import java.util.List;
import java.util.stream.Collectors;

public class RecognizeResultParser {

	public static List<String> ingredients(List<String> recognizeResult) {
		return recognizeResult.stream()
				.skip(1)
				.filter((String line) -> line != null && !line.isEmpty() && line.contains(":"))
				.map((String line) -> line.substring(0, line.indexOf(':')))
				.collect(Collectors.toList());
	}
}
