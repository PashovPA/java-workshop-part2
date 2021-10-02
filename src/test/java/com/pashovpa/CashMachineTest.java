package com.pashovpa;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashMachineTest {

  @Test
  public void exchange() {
    System.out.println("Such a test is expected: amount = 5, values = 3 2");
    var expected = new CashMachine().exchange();

    ArrayList<Long> actualSubList = new ArrayList<>();
    actualSubList.add(3L);
    actualSubList.add(2L);
    ArrayList<ArrayList<Long>> actual = new ArrayList<>();
    actual.add(actualSubList);

    assertEquals(expected, actual);
  }
}

