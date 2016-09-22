package com.bigohk.strings.depthsearch;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class DFTraverseDictionaryWordsTest {

  private static class DictionaryValidator implements Validator {
    private final TreeSet<String> dictionary;

    DictionaryValidator(TreeSet<String> dictionary) {
      this.dictionary = dictionary;
    }

    public boolean isValid(char[] expr, int from, int to) {
      StringBuilder builder = new StringBuilder();
      for (int idx = from; idx <= to; idx++) {
        builder.append(expr[idx]);
      }
      return dictionary.contains(builder.toString());
    }
  }

  private static class DictionaryCollector implements ResultCollector {
    private LinkedList<LinkedList<String>> results = new LinkedList<>();
    private LinkedList<String> currentResults;

    DictionaryCollector(LinkedList<LinkedList<String>> results) {
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
  public void testDictWords() {
    TreeSet<String> dictionary = new TreeSet<>();
    dictionary.addAll(Arrays.asList("i", "like", "DFS", "D", "F", "S", "traversal"));
    Validator validator = new DictionaryValidator(dictionary);

    final LinkedList<LinkedList<String>> results   = new LinkedList<>();
    ResultCollector                      collector = new DictionaryCollector(results);

    String     expr     = "ilikeDFStraversal";
    DFTraverse traverse = new DFTraverse(expr, validator, collector);
    traverse.search();
    assertEquals(2, results.size());
    for (LinkedList<String> list : results) {
      StringBuilder builder = new StringBuilder();
      for (String s : list) {
        builder.append(s);
      }
      assertEquals(expr,builder.toString());
    }
  }

}