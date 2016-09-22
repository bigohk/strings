package com.bigohk.strings.depthsearch;

public class DFTraverse {
  private final Validator       validator;
  private final ResultCollector collector;
  private final char[]          expr;

  public DFTraverse(String e, Validator v, ResultCollector rc) {
    validator = v;
    expr = e.toCharArray();
    collector = rc;
  }

  public void search() {
    search(expr, new int[expr.length], 0);
  }

  private void search(char[] expr, int[] parts, int from) {
    if (from == expr.length) {
      collect(expr, parts);
      return;
    }

    for (int to = from; to < expr.length; ++to) {
      if (validator.isValid(expr, from, to)) {
        parts[from] = to;
        search(expr, parts, to + 1);
        parts[from] = from;
      }
    }
  }

  private void collect(char[] expr, int[] parts) {
    collector.start();
    for (int i = 0; i < parts.length; ) {
      collector.collect(expr, i, parts[i]);
      if (i == parts[i]) ++i;
      else i = parts[i] + 1;
    }
    collector.complete();
  }
}