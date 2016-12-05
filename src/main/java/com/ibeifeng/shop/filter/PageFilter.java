package com.ibeifeng.shop.filter;

import com.ibeifeng.shop.util.PageSystemContext;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/25.
 */
public class PageFilter implements Filter {
    int pageSize=5;
    public void init(FilterConfig filterConfig) throws ServletException {
        String strPageSize=filterConfig.getInitParameter("pageSize");
        if(strPageSize!=null&&!strPageSize.equals("")){
            pageSize=Integer.parseInt(strPageSize);
        }
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        int pageIndex=1;
        try{
            String strPageIndex=req.getParameter("pageIndex");
            if (strPageIndex!=null&&!"".equals(strPageIndex)){
                pageIndex=Integer.parseInt(strPageIndex);
            }
            String strPageSize=req.getParameter("pageSize");
            if (strPageSize!=null&&!"".equals(strPageSize)){
                pageSize=Integer.parseInt(strPageSize);
            }
            PageSystemContext.setPageIndex(pageIndex);
            PageSystemContext.setPageSize(pageSize);
            chain.doFilter(req,resp);
        }catch (Exception e){

        }finally {

            PageSystemContext.removePageIndex();
            PageSystemContext.removePageSize();
        }
    }
    public void destroy() {

    }
}
