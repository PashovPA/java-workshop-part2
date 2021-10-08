package com.pashovpa.firstproblem;

import com.pashovpa.firstproblem.CashMachine;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashMachineTest {

  @Test
  public void exchange() {
    CashMachine test = new CashMachine();

    ByteArrayInputStream in = new ByteArrayInputStream("5\n 3 2\n".getBytes());
    test.inputValues(in);
    var expected = test.exchange();

    ArrayList<Long> actualSubList = new ArrayList<>();
    actualSubList.add(3L);
    actualSubList.add(2L);
    ArrayList<ArrayList<Long>> actual = new ArrayList<>();
    actual.add(actualSubList);

    assertEquals(expected, actual);
  }
}
