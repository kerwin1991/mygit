package com.tujia.web;

/**
 * @author xiaopengw
 * @date 2018/7/27
 * @remark
 */
public class ListRequest<T> {
    /**
     * 查询条件
     */
    private T filter;
    private int isNeedConfig;
    private int pageIndex;
    private String pageKey;
    private String tabKey;

    public T getFilter() {
        return filter;
    }

    public void setFilter(T filter) {
        this.filter = filter;
    }

    public int getIsNeedConfig() {
        return isNeedConfig;
    }

    public void setIsNeedConfig(int isNeedConfig) {
        this.isNeedConfig = isNeedConfig;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageKey() {
        return pageKey;
    }

    public void setPageKey(String pageKey) {
        this.pageKey = pageKey;
    }

    public String getTabKey() {
        return tabKey;
    }

    public void setTabKey(String tabKey) {
        this.tabKey = tabKey;
    }
}
