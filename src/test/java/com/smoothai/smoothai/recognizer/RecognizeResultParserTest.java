package com.smoothai.smoothai.recognizer;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class RecognizeResultParserTest {

	@Test
	public void skipsFirstLine() {
		assertFalse(RecognizeResultParser.ingredients(
				Arrays.asList("skip: but valid", "non-skip: also valid")).contains("skip"));
	}

	@Test
	public void skipsInvalidLines() {
		RecognizeResultParser.ingredients(
				Arrays.asList(
						"skip: valid but first", 
						"non-skip: also valid",
						"should be skipped as no colon",
						"ending: line valid, followed by empty line",
						""));
	}
}
