package com.pashovpa.thirdproblem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DirectoryReaderTest {
  private final String existFilePath = ".\\src\\test\\resources\\TestDirectory\\ExistFile.txt";
  private DirectoryReader testDirectoryReader;

  @BeforeAll
  public void setUp() {
    testDirectoryReader = new DirectoryReader(".\\src\\test\\resources\\TestDirectory");
  }

  @Test
  public void testGetStringFromList() {
    String expected = "string1\nstring2\nstring3\n";
    List<String> testList = new ArrayList<>();
    testList.add("string1");
    testList.add("string2");
    testList.add("string3");
    String actual = DirectoryReader.getStringFromList(testList);

    assertEquals(expected, actual);
  }

  @Test
  public void testGetList() {
    List<String> expected = new ArrayList<>();
    expected.add(".\\src\\test\\resources\\TestDirectory\\ExistFile.txt");
    expected.add(".\\src\\test\\resources\\TestDirectory\\InsertDirectory");
    expected.add(".\\src\\test\\resources\\TestDirectory\\InsertDirectory\\ExistInsertFile.txt");
    List<String> actual = testDirectoryReader.getList();

    assertEquals(expected, actual);
  }

  @Test
  public void testVerifyDirectoryDoesNotExist() {
    assertThrows(FileNotFoundException.class, () -> DirectoryReader.verifyDirectory(""));
  }

  @Test
  public void testVerifyDirectoryIsNotDirectory() {
    assertThrows(IllegalArgumentException.class, () -> DirectoryReader.verifyDirectory(existFilePath));
  }
}
