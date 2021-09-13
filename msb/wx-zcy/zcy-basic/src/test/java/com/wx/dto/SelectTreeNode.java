package com.wx.dto;

import java.util.LinkedList;
import java.util.Objects;

public class SelectTreeNode {

    private String code;

    private String desc;

    private LinkedList<SelectTreeNode> childs;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LinkedList<SelectTreeNode> getChilds() {
        return childs;
    }

    public void setChilds(LinkedList<SelectTreeNode> childs) {
        this.childs = childs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectTreeNode treeNode = (SelectTreeNode) o;
        return Objects.equals(code, treeNode.code) &&
                Objects.equals(desc, treeNode.desc) &&
                Objects.equals(childs, treeNode.childs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, desc, childs);
    }
}
