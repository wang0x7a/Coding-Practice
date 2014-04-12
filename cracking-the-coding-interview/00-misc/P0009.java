/**
 * P0009
 *
 * Insert Interval
 * Given a set of non-overlapping and sorted intervals, insert a new interval
 * into the intervals (merge if necessary)
 *
 * Exeample:
 * Given intervals [1, 3], [6, 9] and inerst [2, 5] in as [1, 5], [6, 9]
 */

import java.util.ArrayList;

public class P0009 {
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval ins) {
        ArrayList<Interval> res = new ArrayList<Interval>();

        if (intervals == null || intervals.size() < 1) {
            res.add(ins);
            return res;
        }

        if (ins == null)
            return intervals;

        for (Interval curr : intervals) {
            if (curr.end < ins.start) {
                res.add(curr);
            }
            else if (curr.start > ins.end) {
                res.add(ins);
                ins = curr;
            }
            else if (curr.end >= ins.start || curr.start <= ins.end) {
                ins = new Interval(Math.min(curr.start, ins.start),
                                   Math.max(curr.end, ins.end));
            }
        }
        res.add(ins);
        
        return res;
    }

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

    public void print(ArrayList<Interval> intervals) {
        for (Interval intvl : intervals) {
        System.out.print("[" + intvl.start + "," + intvl.end + "] ");
        }

        System.out.println();
    }

    public static void main(String args[]) {
        String[] strIntvls = {"1,3", "6,9"}; 
        Interval ins = new Interval(2, 5);

        P0009 p0009 = new P0009();
        ArrayList<Interval> intervals = p0009.makeIntervals(strIntvls);
        p0009.print(intervals);
        ArrayList<Interval> res = p0009.insert(intervals, ins);
        p0009.print(res);
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
