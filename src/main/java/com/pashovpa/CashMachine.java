package com.pashovpa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CashMachine {
  private long amount;
  private long[] values;

  public CashMachine() {
    try {
      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Input amount: ");

      String amountStrInput;
      long[] amountLongInput;
      int amountError = 1;

      while (amountError != 0) {
        amountStrInput = input.readLine().trim();
        if ((!amountStrInput.matches("\\d+"))) {
          System.out.print("Invalid value! Input must contain only numbers without other characters! Re-enter: ");
          continue;
        }
        amountLongInput = Arrays.stream(amountStrInput.split("\\s+")).mapToLong(Long::parseLong).toArray();
        if (amountLongInput.length != 1 || amountLongInput[0] <= 0) {
          System.out.print("Invalid value! A number must be integer and greater than zero! Re-enter: ");
          continue;
        }
        this.amount = amountLongInput[0];
        amountError = 0;
      }

      System.out.print("Input values: ");

      String valuesStrInput;
      long[] valuesLongInput;
      int valuesError = 1;

      while (valuesError != 0) {
        valuesStrInput = input.readLine().trim();
        if ((!valuesStrInput.replaceAll("\\s+", "").matches("\\d+"))) {
          System.out.print("Invalid value! Input must contain only numbers without other characters! Re-enter: ");
          continue;
        }
        valuesLongInput = Arrays.stream(valuesStrInput.split("\\s+")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(valuesLongInput);
        if (valuesLongInput[0] <= 0) {
          System.out.print("Invalid value! Numbers must be integer and greater than zero! Re-enter: ");
          continue;
        }
        this.values = Arrays.stream(valuesLongInput).distinct().toArray();
        valuesError = 0;
      }
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
    while (true) {
      var testResult = new CashMachine().exchange();
      System.out.println("Number of combinations: " + testResult.size());
      System.out.println("Combinations: " + testResult);
      System.out.println("===============================================");
    }
  }
}