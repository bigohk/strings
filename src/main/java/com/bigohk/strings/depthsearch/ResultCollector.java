package com.bigohk.strings.depthsearch;

public interface ResultCollector {
  void start();
  void collect(char[] expr, int from, int to);
  void complete();
}