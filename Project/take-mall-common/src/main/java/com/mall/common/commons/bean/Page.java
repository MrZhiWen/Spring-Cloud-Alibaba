package com.mall.common.commons.bean;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {

    private int currentPage; //当期页
    private int pageSize; //每页记录量
    private int totalRow; //总记录数
    private int offset; //起始偏移量
    private int totalPage; //总页码
    private QueryParam param;


    private List<T> data = new ArrayList<>();

    public Page() {
    }

    public Page(QueryParam param, int totalRow, List<T> data) {
        this.pageSize = param.getPageSize();
        this.offset = param.getOffset();
        this.currentPage = offset / pageSize + 1;
        this.totalRow = totalRow;
        this.totalPage = totalRow % pageSize == 0 ? totalRow / pageSize : totalRow / pageSize + 1;
        this.data = data;
        this.param = param;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public QueryParam getParam() {
        return param;
    }

    public void setParam(QueryParam param) {
        this.param = param;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean hasResult(){
        return !CollectionUtils.isEmpty(data);
    }

    public boolean isHasNext(){
        return totalPage > 1 && totalPage > currentPage;
    }

    public boolean isHasPrevious(){
        return currentPage <= totalPage && currentPage > 1;
    }

    public boolean isHasPaging(){
        return totalPage > 1;
    }
}
