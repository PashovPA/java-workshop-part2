package com.pashovpa.thirdproblem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DirectoryReader {
  private final String path;
  private final File directory;

  public DirectoryReader(String path) throws FileNotFoundException {
    File checkedDirectory = new File(path);
    verifyDirectory(checkedDirectory);

    this.path = path;
    this.directory = checkedDirectory;
  }

  public String toString() {
    List<String> directoryList = getDirectoryListing(directory);
    StringBuilder sb = new StringBuilder();
    for (String filePath : directoryList) {
      sb.append(filePath).append('\n');
    }

    return sb.toString();
  }

  private List<String> getDirectoryListing(File directory) {
    List<String> result = new ArrayList<>();
    File[] files = directory.listFiles();
    for (File f : files) {
      if (f.isDirectory()) {
        result.addAll(getDirectoryListing(f));
      }
      if (f.isFile()) {
        result.add(f.getPath());
      }
    }
    return result;
  }

  private void verifyDirectory(File directory) throws FileNotFoundException {
    if (!directory.exists()) {
      throw new FileNotFoundException("Such directory does not exist: " + directory.getPath());
    }
    if (!directory.isDirectory()) {
      throw new IllegalArgumentException("Is not a directory: " + directory.getPath());
    }
  }

  public static void main(String[] args) {
    try {
      DirectoryReader test = new DirectoryReader(".\\src");
      System.out.println(test);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}


