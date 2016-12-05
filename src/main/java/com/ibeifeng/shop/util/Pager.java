package com.ibeifeng.shop.util;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */
public class Pager<T> {
    private int pageSize;
    private int pageIndex;
    private int totalPage;
    private int totalCount;
    private List<T> datas;

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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public Pager() {
    }

    public Pager(int pageSize, int pageIndex, int totalPage, int totalCount, List<T> datas) {

        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.datas = datas;
    }
}
