package com.tujia.dto;

/**
 * @author xiaopengw
 * @date 2018/8/16
 * @remark
 */
public class SubmitRequest<T> {

    private int pageSize;
    private int pageIndex;
    private T filter;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public T getFilter() {
        if (this.filter == null) {
            // return this.filter.getClass().newInstance();
        }
        return filter;
    }

    public static <E> E newInstance(Class<E> clazz) throws Exception {

        return clazz.newInstance();
    }

    public void setFilter(T filter) {
        this.filter = filter;
    }
}
