package com.pashovpa.fifthproblem;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordParser {

  public static Map<String, Integer> countWordsInFile(String filePath) throws IOException {
    if (!Files.exists(Paths.get(filePath))) {
      throw new FileNotFoundException("Such file does not exist!");
    }
    if (Files.isDirectory(Paths.get(filePath))) {
      throw new IllegalArgumentException("Such file is directory!");
    }

    List<String> wordsList = Files.lines(Paths.get(filePath))
        .flatMap(s -> Stream.of(s.split("\\p{Punct}|\\s+")).filter(p -> p.length() != 0))
        .collect(Collectors.toList());

    Map<String, Integer> wordsCounter = wordsList.stream()
        .collect(HashMap::new, (m, c) -> m.put(c, m.containsKey(c) ? (m.get(c) + 1) : 1), HashMap::putAll);

    return wordsCounter;
  }

  public static void writeToFile(String filePath, Map<String, Integer> map) {
    if (Files.exists(Path.of(filePath))) {
      throw new IllegalArgumentException("File exists! Can not be rewritten!");
    }

    try (FileWriter fileWriter = new FileWriter(filePath)) {
      for (var entry : map.entrySet()) {
        fileWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void createFiles(String directoryPath, Map<String, Integer> map, int nThreads) {
    if (nThreads <= 0) {
      throw new IllegalArgumentException("Number of threads must be greater than zero!");
    }

    Map<String, CompletableFuture<String>> completableFutureMap = new HashMap<>();
    for (var entry : map.entrySet()) {
      completableFutureMap.put(entry.getKey(), CompletableFuture.supplyAsync(() -> {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < entry.getValue(); i++) {
          stringBuilder.append(entry.getKey()).append(" ");
        }
        return stringBuilder.toString();
      }));
    }

    for (var entryFuture : completableFutureMap.entrySet()) {
      try (FileWriter fileWriter = new FileWriter(directoryPath + "\\" + entryFuture.getKey() + ".txt")) {
        fileWriter.write(entryFuture.getValue().get());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws IOException {
    var result = WordParser.countWordsInFile(".\\src\\test\\resources\\WordParserTest.txt");
    WordParser.writeToFile(".\\src\\test\\resources\\counts.txt", result);
    WordParser.createFiles(".\\src\\test\\resources\\WordParserTest", result, 10);
  }
}
