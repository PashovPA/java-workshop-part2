package com.pashovpa.fourthproblem;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MultithreadedTangentTest {
  private final String argumentFilePath = ".\\src\\test\\resources\\MultiTangentArguments.txt";
  private List<Double> arguments;

  public void setUp(int amountOfNumbers) {
    try (FileWriter fileWriter = new FileWriter(argumentFilePath)) {
      for (int i = 0; i < amountOfNumbers; i++) {
        fileWriter.write(i + " ");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.arguments = MultithreadedTangent.getArgumentsListFromFile(argumentFilePath);
  }

  @Test
  public void test10() {
    setUp(10);
    long start, end;

    start = System.currentTimeMillis();
    MultithreadedTangent.tan(arguments, 1);
    end = System.currentTimeMillis();
    System.out.println("Execution time for N = 10 in single-threaded mode: " + (end - start));

    start = System.currentTimeMillis();
    MultithreadedTangent.tan(arguments, 10);
    end = System.currentTimeMillis();
    System.out.println("Execution time for N = 10 in multithreaded mode: " + (end - start));
  }

  @Test
  public void test100() {
    setUp(100);
    long start, end;

    start = System.currentTimeMillis();
    MultithreadedTangent.tan(arguments, 1);
    end = System.currentTimeMillis();
    System.out.println("Execution time for N = 100 in single-threaded mode: " + (end - start));

    start = System.currentTimeMillis();
    MultithreadedTangent.tan(arguments, 10);
    end = System.currentTimeMillis();
    System.out.println("Execution time for N = 100 in multithreaded mode: " + (end - start));
  }

  @Test
  public void test1000000() {
    setUp(1000000);
    long start, end;

    start = System.currentTimeMillis();
    MultithreadedTangent.tan(arguments, 1);
    end = System.currentTimeMillis();
    System.out.println("Execution time for N = 1000000 in single-threaded mode: " + (end - start));

    start = System.currentTimeMillis();
    MultithreadedTangent.tan(arguments, 10);
    end = System.currentTimeMillis();
    System.out.println("Execution time for N = 1000000 in multithreaded mode: " + (end - start));
  }
}
