package com.wx.class03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 堆
 * 模拟抽奖
 */
public class EveryStepShowBoss {

    public static class Customer{
        // 用户id
        public int id;
        // 购买数量
        public int buy;
        // 进入中奖区 候选区的时间
        public int enterTime;

        public Customer(int id, int buy, int enterTime) {
            this.id = id;
            this.buy = buy;
            this.enterTime = enterTime;
        }
    }

    /**
     * 候选区 比较器
     * 排在前面的优先进入中奖区
     */
    public static class CandidateComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            // 根据购买数量 降序   进入时间 升序 排序
            return o1.buy != o2.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
        }
    }

    /**
     * 得奖区 比较器
     * 排在前面的优先被替换
     */
    public static class DaddyComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            // 根据购买数量 升序   进入时间 升序 排序
            return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
        }
    }

    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        WhosYourDaddy whosYourDaddy = new WhosYourDaddy(k);
        for (int i = 0; i < arr.length; i++) {
            whosYourDaddy.operate(i, arr[i], op[i]);
            ans.add(whosYourDaddy.getDaddies());
        }
        return ans;
    }


    public static class WhosYourDaddy{
        //
        private HashMap<Integer, Customer> customers;
        // 候选区 大根堆
        private HeapGreater<Customer> candHeap;
        // 中奖区 小根堆
        private HeapGreater<Customer> daddyHeap;
        // 得奖区多大
        private final int daddyLimit;

        public WhosYourDaddy(int daddyLimit) {
            this.daddyLimit = daddyLimit;
            customers = new HashMap<Integer, Customer>();
            candHeap = new HeapGreater<>(new CandidateComparator());
            daddyHeap = new HeapGreater<>(new DaddyComparator());
        }


        // o(n*(logn+logk+k))  得奖区的复杂度 logk  候选区logn  k
        // 复杂度 n*logn

        // 当前处理I事件，arr[i] -> id
        public void operate(int time, int id, boolean buyOrRefund) {
            // 没买 且 不在两个区域 说明没有购买行为 却发生了退货行为 忽略
            if (!buyOrRefund && customers.containsKey(id)) {
                return;
            }
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            Customer c = customers.get(id);
            if (buyOrRefund) {
                c.buy++;
            } else {
                c.buy--;
            }
            // 购买成0了
            if (c.buy == 0) {
                customers.remove(id);
            }
            // 找到一个区域添加进去 如果已经在某个区域 更新里面的值 并调整堆结构 如果某人购买为0了直接移除
            if (!candHeap.contains(c) && !daddyHeap.contains(c)) {
                // 都没有
                // 判断中奖区是否达到了K 没有直接添加进去
                if (daddyHeap.size() < daddyLimit) {
                    c.enterTime = time;
                    daddyHeap.push(c);
                } else {
                    // 否则 进入候选区待命
                    c.enterTime = time;
                    candHeap.push(c);
                }
            } else if (candHeap.contains(c)) {
                // 在候选区
                if (c.buy == 0) {
                    // 购买数0 删除 高效
                    candHeap.remove(c);
                } else {
                    // 购买数变化了 调整堆 logn
                    candHeap.resign(c);
                }
            } else {
                // 在得奖区
                if (c.buy == 0) {
                    // 购买数0 删除 高效
                    daddyHeap.remove(c);
                } else {
                    // 购买数变化了 调整堆 logn
                    daddyHeap.resign(c);
                }
            }
            // 存在元素调整的情况 从中奖区到候选区 候选区到中奖区
            daddyMove(time);
        }

        private void daddyMove(int time) {
            // 候选区没有元素 退出了
            if (candHeap.isEmpty()) {
                return;
            }
            if (daddyHeap.size() < daddyLimit) {
                // 中奖区元素少了 直接从候选区弹出一个放进中奖区
                Customer p = candHeap.pop();
                p.enterTime = time;
                daddyHeap.push(p);
            } else {
                // 如果候选区(大根堆)的顶部购买数量大于中奖区的顶部（小根堆） 替换
                if (candHeap.peek().buy > daddyHeap.peek().buy) {
                    Customer oldDaddy = daddyHeap.pop();
                    Customer newDaddy = candHeap.pop();
                    oldDaddy.enterTime = time;
                    newDaddy.enterTime = time;
                    daddyHeap.push(newDaddy);
                    candHeap.push(oldDaddy);
                }
            }
        }

        public List<Integer> getDaddies() {
            List<Customer> allElement = daddyHeap.getAllElement();
            List<Integer> ans = new ArrayList<>();
            for (Customer customer : allElement) {
                ans.add(customer.id);
            }
            return ans;
        }
    }

}
