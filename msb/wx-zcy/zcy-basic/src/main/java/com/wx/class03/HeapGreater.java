package com.wx.class03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 增强堆
 *
 * 手写
 *
 * @param <T>
 */
public class HeapGreater<T> {

    // 数组 T 一定是非基础类型，有基础类型需求，包一层
    private ArrayList<T> heap;
    // 反向索引表
    private HashMap<T, Integer> indexMap;
    // 堆大小
    private int heapSize;
    // 怎么比大小
    private Comparator<? super T> comp;

    public HeapGreater(ArrayList<T> heap, HashMap<T, Integer> indexMap, int heapSize, Comparator<? super T> comp) {
        this.heap = heap;
        this.indexMap = indexMap;
        this.heapSize = heapSize;
        this.comp = comp;
    }

    public HeapGreater(Comparator<? super T> comp) {
        this.comp = comp;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++); // heapsize增加了1
    }

    /**
     *
     * @param idx 该位置是新加的元素
     */
    private void heapInsert(int idx) {
        // 当前节点 需要放在父节点的前面的时候 跟 父节点交换
        while (comp.compare(heap.get(idx), heap.get((idx-1)/2)) < 0) {
            // 和父节点交换
            swap(idx, (idx-1)/2);
            // 来到父节点位置
            idx = (idx-1)/2;
        }
    }

    public T pop() {
        T ans = heap.get(0);

        // 交换0和最后的位置
        swap(0, heapSize-1);
        // 删除反向索引里的关系
        indexMap.remove(ans);
        // 从堆里删除
        heap.remove(--heapSize);
        //
        heapify(0);

        return ans;
    }

    /**
     * 系统实现的堆，是没有这个方法的
     *
     * o(logn)复杂度
     *
     * 一个对象属性变化了，重新调整堆结构
     * @param obj
     */
    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    /**
     * 返回堆上所有元素
     * @return
     */
    public List<T> getAllElement() {
        List<T> ans = new ArrayList<>();
        for (T c : heap) {
            ans.add(c);
        }
        return ans;
    }


    private void heapify(int idx) {
        int left = idx * 2 + 1;
        while (left < heapSize) {
            // 找到应该上升的孩子：有右孩子，且右孩子排序应该靠上 返回右孩子，否则 返回左孩子
            int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
            // 还在跟当前索引对比，返回应该靠前的索引
            best = comp.compare(heap.get(best), heap.get(idx)) < 0 ? best : idx;
            if (best == idx) {
                break;
            }
            swap(best, idx);
            idx = best;
            left = idx * 2 + 1;
        }
    }

    /**
     * 系统实现的堆，是没有这个方法的
     * o(logn)复杂度 实现随机删除某个元素，并调整堆结构
     *
     * 删除过程：根据反向索引，找到对象所在的索引i，将i跟最后一个元素交换
     * 删掉最后一个，i位置resign
     *
     */
    public void remove(T obj) {
        // 最后一个
        T replace = heap.get(heapSize - 1);
        // 被删除位置
        int idx = indexMap.get(obj);
        // 删除索引
        indexMap.remove(obj);
        // 删除元素
        heap.remove(--heapSize);
        // 可能要删除的本来就是最后一个元素，需要判断一下
        // 边界考察
        if (obj != replace) {
            heap.set(idx, replace);
            indexMap.put(replace, idx);
            // 重新调整
            resign(replace);
        }



    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(j, o1);
        heap.set(i, o2);
        indexMap.put(o2, i);
        indexMap.put(o1, j);

    }


    /**
     * 数组 T 一定是非基础类型，有基础类型需求，包一层
     * @param <V>
     */
    public static class Inner<V>{
        private V value;

        public Inner(V v) {
            value = v;
        }
    }




}
