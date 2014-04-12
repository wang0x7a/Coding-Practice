/* P0008
 *
 * Merge Intervals
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * For example,
 * Given [1, 3], [2, 6], [8, 10], [15, 18]
 * return [1, 6], [8, 10], [15, 18]
 * */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class P0008 {
  public ArrayList<Interval> makeIntervals(String[] strIntvls) {
    int len = strIntvls.length;

    ArrayList<Interval> res = new ArrayList<Interval>(len);
    for (String intvl : strIntvls) {
      String[] a = intvl.split(",");
      int start  = Integer.parseInt(a[0]);
      int end    = Integer.parseInt(a[1]);

      Interval interval = new Interval(start, end);
      res.add(interval);
    }

    return res;
  }

  public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
    if (intervals == null)
      return null;

    int len = intervals.size();
    if (len == 0)
      return intervals;

    Collections.sort(intervals, new IntervalComparator());

    ArrayList<Interval> res = new ArrayList<Interval>();

    Interval prev = intervals.get(0);
    for (int i = 1; i < len; i++) {
      Interval curr = intervals.get(i);

      if (curr.start <= prev.end) {
        Interval merged = new Interval(prev.start, Math.max(prev.end, curr.end));
        prev = merged;
      }
      else {
        res.add(prev);
        prev = curr;
      }
    }

    res.add(prev);

    return res;
  }

  public void print(ArrayList<Interval> intervals) {
    for (Interval intvl : intervals) {
      System.out.print("[" + intvl.start + "," + intvl.end + "] ");
    }

    System.out.println();

  }

  public static void main(String[] args) {
    String[] strIntvls = {"1,3", "2,6", "8,10", "15,18"};

    P0008 p0008 = new P0008();
    ArrayList<Interval> intvls = p0008.makeIntervals(strIntvls);
    p0008.print(intvls);
    ArrayList<Interval> res = p0008.merge(intvls);
    p0008.print(res);
  }
}

class Interval {
  public int start;
  public int end;

  public Interval() {}

  public Interval(int start, int end) {
    this.start = start;
    this.end   = end;
  }
}

class IntervalComparator implements Comparator<Interval> {
  public int compare(Interval i1, Interval i2) {
    return i1.start - i2.start;
  }
}
