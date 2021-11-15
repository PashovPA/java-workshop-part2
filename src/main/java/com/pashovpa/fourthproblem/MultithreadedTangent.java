package com.pashovpa.fourthproblem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultithreadedTangent {

  public static List<Double> getArgumentsListFromFile(String fileName) {
    List<Double> argumentsList = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
      String argumentsString;
      double[] argumentsDoubleRow;
      while ((argumentsString = bufferedReader.readLine()) != null) {
        if ((!argumentsString.replaceAll("\\s+", "").replaceAll("\\.", "").matches("\\d+"))) {
          throw new IOException("Invalid value! File must contain only numbers without other characters!");
        }
        argumentsDoubleRow = Arrays.stream(argumentsString.trim().split("\\s+")).mapToDouble(Double::parseDouble).toArray();
        for (double arg : argumentsDoubleRow) {
          argumentsList.add(arg);
        }
      }
    } catch (IOException e) {
      System.out.println("Input error!");
      e.printStackTrace();
    }
    return argumentsList;
  }

  public static List<Double> tan(List<Double> arguments, int nThreads) {
    if (nThreads == 0) {
      throw new IllegalArgumentException("Number of threads must be greater than zero!");
    }

    int div = arguments.size() / nThreads;
    int mod = arguments.size() % nThreads;
    int[] indexArray = new int[arguments.size()];
    Arrays.fill(indexArray, div);
    for (int i = 0; i < mod; i++) {
      indexArray[i]++;
    }

    double[] values = new double[arguments.size()];
    ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
    for (int i = 0; i < nThreads; i++) {
      int finalI = i;
      executorService.execute(() -> {
        int sumIndex = 0;
        for (int j = 0; j < finalI; j++) {
          sumIndex += indexArray[j];
        }
        for (int j = 0; j < indexArray[finalI]; j++) {
          values[sumIndex] = Math.tan(arguments.get(sumIndex));
          sumIndex++;
        }
      });
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
    for (double value : values) {
      valuesList.add(value);
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


