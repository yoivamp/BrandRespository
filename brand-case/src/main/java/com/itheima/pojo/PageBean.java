package com.itheima.pojo;

import java.util.List;

/**
 * 分页查询的包装对象
 * @author 喵vamp
 */
public class PageBean<T> {
    /**
     * 总记录数
     */
    private int totalCount;

    /**
     * 当前页数据
     */
    private List<T> rows;

    public PageBean() {
    }

    public PageBean(int totalCount, List<T> rows) {
        this.totalCount = totalCount;
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
