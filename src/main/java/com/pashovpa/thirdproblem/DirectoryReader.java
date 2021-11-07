package com.pashovpa.thirdproblem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DirectoryReader {
  private final File directory;

  public DirectoryReader(String path) {
    this.directory = new File(path);
  }

  public static String getStringFromList(List<String> directoryListing) {
    StringBuilder sb = new StringBuilder();
    for (String filePath : directoryListing) {
      sb.append(filePath).append('\n');
    }
    return sb.toString();
  }

  public List<String> getList() {
    return getList(directory);
  }

  private List<String> getList(File directory) {
    List<String> result = new ArrayList<>();
    File[] files = directory.listFiles();
    for (File f : files) {
      if (f.isDirectory()) {
        result.add(f.getPath());
        result.addAll(getList(f));
      }
      if (f.isFile()) {
        result.add(f.getPath());
      }
    }
    return result;
  }

  public static void verifyDirectory(String path) throws FileNotFoundException {
    File directory = new File(path);
    if (!directory.exists()) {
      throw new FileNotFoundException("Such directory does not exist: " + directory.getPath());
    }
    if (!directory.isDirectory()) {
      throw new IllegalArgumentException("Is not a directory: " + directory.getPath());
    }
  }

  public static void main(String[] args) {
    if (args.length != 2 || Files.exists(Path.of(args[1]))) {
      throw new IllegalArgumentException("Program arguments must contain exactly two values: existing folder path and non-existent file path!");
    }
    try (FileWriter fileWriter = new FileWriter(args[1])) {
      DirectoryReader.verifyDirectory(args[0]);
      DirectoryReader directoryReader = new DirectoryReader(args[0]);
      fileWriter.write(DirectoryReader.getStringFromList(directoryReader.getList()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


