package com.pashovpa.firstproblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CashMachine {
  private long amount;
  private long[] values;

  public void inputValues(InputStream in) {
    try {
      BufferedReader input = new BufferedReader(new InputStreamReader(in));
      System.out.print("Input amount: ");

      String amountStrInput = input.readLine().trim();
      if ((!amountStrInput.matches("\\d+"))) {
        throw new IOException("Invalid value! Input must contain a number without other characters!");
      }
      long[] amountLongInput = Arrays.stream(amountStrInput.split("\\s+")).mapToLong(Long::parseLong).toArray();
      this.amount = amountLongInput[0];

      System.out.print("Input values: ");

      String valuesStrInput = input.readLine().trim();
      if ((!valuesStrInput.replaceAll("\\s+", "").matches("\\d+"))) {
        throw new IOException("Invalid value! Input must contain only numbers without other characters!");
      }
      long[] valuesLongInput = Arrays.stream(valuesStrInput.split("\\s+")).mapToLong(Long::parseLong).toArray();
      Arrays.sort(valuesLongInput);
      if (valuesLongInput[0] <= 0) {
        throw new IOException("Invalid value! Numbers must be integer and greater than zero!");
      }
      this.values = Arrays.stream(valuesLongInput).distinct().toArray();
    } catch (Exception e) {
      System.out.println("Input error!");
      e.printStackTrace();
    }
  }

  public ArrayList<ArrayList<Long>> exchange() {
    return exchange(this.amount, this.values[this.values.length - 1]);
  }

  private ArrayList<ArrayList<Long>> exchange(long amount, long maxCoin) {
    ArrayList<ArrayList<Long>> result = new ArrayList<>();
    if (amount == 0) {
      result.add(new ArrayList<>());
    } else {
      for (int i = this.values.length - 1; i >= 0; i--) {
        long coin = values[i];
        if (coin > amount || coin > maxCoin) {
          continue;
        }
        for (ArrayList<Long> remain : exchange(amount - coin, coin)) {
          ArrayList<Long> set = new ArrayList<>();
          set.add(coin);
          set.addAll(remain);
          result.add(set);
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    CashMachine test = new CashMachine();
    test.inputValues(System.in);
    var result = test.exchange();
    System.out.println("Number of combinations: " + result.size());
    System.out.println("Combinations: " + result);
    System.out.println("===============================================");

  }
}