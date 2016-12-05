package com.ibeifeng.shop.util;

/**
 * Created by Administrator on 2016/11/25.
 */
public class PageSystemContext {
    private static ThreadLocal<Integer> pageSize =new ThreadLocal<Integer>();
    private static ThreadLocal<Integer> pageIndex =new ThreadLocal<Integer>();

    public static int getPageSize() {
        return pageSize.get();
    }

    public static void setPageSize(int _pageSize) {
        pageSize.set(_pageSize);
    }

    public static int getPageIndex() {
        return pageIndex.get();
    }

    public static void setPageIndex(int _pageIndex) {
        pageIndex.set(_pageIndex);
    }

    public static void removePageSize(){
        pageSize.remove();
    }
    public static void removePageIndex(){
        pageIndex.remove();
    }
}
