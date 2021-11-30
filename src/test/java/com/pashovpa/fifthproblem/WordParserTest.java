package com.pashovpa.fifthproblem;

import com.pashovpa.thirdproblem.DirectoryReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WordParserTest {
  private final String wordsFilePath = ".\\src\\test\\resources\\WordParserTest\\WordParserTest.txt";
  private final String notExistingFilePath = ".\\src\\test\\resources\\WordParserTest\\NotExistingFile.txt";
  private final String createdFilesDirectoryPath = ".\\src\\test\\resources\\WordParserTest\\CreatedFiles";
  private Map<String, Integer> expected;

  @BeforeAll
  public void setUp() {
    Map<String, Integer> expected = new HashMap<>();
    expected.put("foo", 3);
    expected.put("bar", 2);
    expected.put("baz", 1);
    this.expected = expected;
  }

  @Test
  public void testCountWordsInFile() throws IOException {
    Map<String, Integer> actual = WordParser.countWordsInFile(wordsFilePath);
    assertEquals(expected, actual);
  }

  @Test
  public void testCreateFiles() throws IOException {
    WordParser.createFiles(createdFilesDirectoryPath, expected, 10);
    String actualFiles = DirectoryReader.getStringFromList(new DirectoryReader(createdFilesDirectoryPath).getList());
    String expectedFiles = ".\\src\\test\\resources\\WordParserTest\\CreatedFiles\\bar.txt\n" +
        ".\\src\\test\\resources\\WordParserTest\\CreatedFiles\\baz.txt\n" +
        ".\\src\\test\\resources\\WordParserTest\\CreatedFiles\\foo.txt\n";

    assertEquals(expectedFiles, actualFiles);
  }

  @Test
  public void testCountWordsInNotExistingFile() {
    assertThrows(FileNotFoundException.class, () -> WordParser.countWordsInFile(notExistingFilePath));
  }

  @Test
  public void testCountWordsInDirectory() {
    assertThrows(IllegalArgumentException.class, () -> WordParser.countWordsInFile(".\\src\\test\\resources\\WordParserTest"));
  }

  @Test
  public void testWriteToExistingFile() {
    assertThrows(IllegalArgumentException.class, () -> WordParser.writeToFile(wordsFilePath, expected));
  }

  @Test
  public void testWriteToDirectory() {
    assertThrows(IllegalArgumentException.class, () -> WordParser.writeToFile(createdFilesDirectoryPath, expected));
  }

  @Test
  public void testCreateFilesInZeroThreads() {
    assertThrows(IllegalArgumentException.class, () -> WordParser.createFiles(createdFilesDirectoryPath, expected, 0));
  }

  @Test
  public void testCreateFilesInNotExistingDirectory() {
    assertThrows(FileNotFoundException.class, () -> WordParser.createFiles(createdFilesDirectoryPath + "NotExist", expected, 10));
  }

  @Test
  public void testCreateFilesInNotADirectory() {
    assertThrows(IllegalArgumentException.class, () -> WordParser.createFiles(wordsFilePath, expected, 10));
  }
}
