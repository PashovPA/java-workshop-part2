package com.pashovpa.thirdproblem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DirectoryReaderTest {
  private DirectoryReader testDirectory;

  @BeforeAll
  public void setUp() throws FileNotFoundException {
    testDirectory = new DirectoryReader("D:\\testDirectory");
  }

  @Test
  public void testToString() {
    String expected = "D:\\testDirectory\\testDirectoryReader.txt\n";
    String actual = testDirectory.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void testVerifyDirectoryDoesNotExist() {
    assertThrows(FileNotFoundException.class, () -> DirectoryReader.verifyDirectory(new File("")));
  }

  @Test
  public void testVerifyDirectoryIsNotADirectory() {
    assertThrows(IllegalArgumentException.class, () -> DirectoryReader.verifyDirectory(new File("D:\\testDirectory\\testDirectoryReader.txt")));
  }
}
