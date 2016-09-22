package com.bigohk.strings.depthsearch;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class DFTraversePalindromesTest {

  private static class PalindromeValidator implements Validator {
    public boolean isValid(char[] expr, int from, int to) {
      if (to <= from) return true;
      return expr[from] == expr[to] && isValid(expr, from + 1, to - 1);
    }
  }

  private static class PalindromeCollector implements ResultCollector {
    private LinkedList<LinkedList<String>> results = new LinkedList<>();
    private LinkedList<String> currentResults;

    PalindromeCollector(LinkedList<LinkedList<String>> results) {
      this.results = results;
    }

    @Override
    public void start() {
      currentResults = new LinkedList<>();
    }

    @Override
    public void collect(char[] expr, int from, int to) {
      StringBuilder builder = new StringBuilder();
      for (int i = from; i <= to; ++i) {
        builder.append(expr[i]);
      }
      currentResults.add(builder.toString());
    }

    @Override
    public void complete() {
      results.add(currentResults);
    }
  }

  @Test
  public void testPalindromes() {
    Validator palindromeValidator = new PalindromeValidator();

    final LinkedList<LinkedList<String>> results            = new LinkedList<>();
    ResultCollector                      palindromCollector = new PalindromeCollector(results);

    String     expr     = "abacusu";
    DFTraverse traverse = new DFTraverse(expr, palindromeValidator, palindromCollector);
    traverse.search();

    assertEquals(4, results.size());

    for (LinkedList<String> list : results) {
      StringBuilder builder = new StringBuilder();
      for (String s : list) {
        builder.append(s);
      }
      assertEquals(expr, builder.toString());
    }
  }
}