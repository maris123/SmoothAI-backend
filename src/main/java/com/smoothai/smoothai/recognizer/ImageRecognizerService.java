package com.smoothai.smoothai.recognizer;

import java.nio.file.Path;
import java.util.List;

public interface ImageRecognizerService {

	List<String> getFruits(Path filePath);
}
