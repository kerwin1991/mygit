package com.wx.class03;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CoverMax {



    // 复杂度  o(n*logn)
    @Test
    public int maxCover(int[][] m){
        // 转换成Line数组
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        // 按照起始位置升序排序
        Arrays.sort(lines, new StartComparator());
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (Line line : lines) {
            while (!heap.isEmpty() && heap.peek() <= line.start) {
                heap.poll();
            }
            heap.add(line.end);

            max = Math.max(max, heap.size());
        }
        return max;
    }


    public class StartComparator implements Comparator<Line>{
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o1.start;
        }
    }

    public static class Line{
        private int start;
        private int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }

}
