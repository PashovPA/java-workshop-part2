package com.pashovpa.fourthproblem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class MultithreadedTangent {

  public static List<Double> getArgumentsListFromFile(String fileName) {
    List<Double> argumentsList = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
      String argumentsString;
      double[] argumentsDoubleRow;
      try {
        while ((argumentsString = bufferedReader.readLine()) != null) {
          argumentsDoubleRow = Arrays.stream(argumentsString.trim().split("\\s+")).mapToDouble(Double::parseDouble).toArray();
          for (double arg : argumentsDoubleRow) {
            argumentsList.add(arg);
          }
        }
      } catch (IOException e) {
        System.err.println("Invalid values! File must contain only real numbers separated by a space without any other characters!");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return argumentsList;
  }

  public static List<Double> tan(List<Double> arguments, int nThreads) {
    if (nThreads <= 0) {
      throw new IllegalArgumentException("Number of threads must be greater than zero!");
    }

    ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
    List<Future<Double>> futures = new ArrayList<>();
    for (int i = 0; i < arguments.size(); i++) {
      int finalI = i;
      futures.add(executorService.submit(() -> Math.tan(arguments.get(finalI))));
    }

    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(100, TimeUnit.SECONDS)) {
        executorService.shutdownNow();
      }
    } catch (InterruptedException e) {
      executorService.shutdownNow();
    }

    List<Double> valuesList = new ArrayList<>();
    for (Future<Double> future : futures) {
      try {
        valuesList.add(future.get());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return valuesList;
  }

  public static void writeToFile(String fileName, List<Double> arguments, List<Double> values) {
    if (Files.exists(Path.of(fileName))) {
      throw new IllegalArgumentException("File exists! Can not be rewritten!");
    }

    try (FileWriter fileWriter = new FileWriter(fileName)) {
      for (int i = 0; i < values.size(); i++) {
        fileWriter.write("x = " + arguments.get(i) + ", tan(x) =  " + values.get(i) + ";\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


