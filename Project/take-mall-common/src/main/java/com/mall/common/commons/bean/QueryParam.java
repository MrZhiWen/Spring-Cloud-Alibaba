package com.mall.common.commons.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public abstract class QueryParam {

    private int currentPage;

    private int pageSize;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime begin;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;

    public LocalDateTime getBegin() {
        if(this.begin != null && this.begin.isAfter(getEnd())){
            this.begin = getEnd();
        }
        return begin;
    }
    protected int offset;

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        if(this.end == null || this.end.isAfter(LocalDateTime.now())){
            return LocalDateTime.now();
        }
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }


    public QueryParam() {
        super();
    }

    public QueryParam(QueryParam param) {
        this.currentPage = param.currentPage;
        this.pageSize = param.pageSize;
    }

    public QueryParam(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public boolean hasPageSize() {
        return pageSize > 0;
    }

    public boolean hasCurrentPage() {
        return currentPage > 0;
    }

    public int getOffset() {
        return getPageSize() * (getCurrentPage() - 1);
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return Math.max(pageSize, 10);
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return Math.max(1, currentPage);
    }

    @Override
    public String toString() {
        return "QueryParam{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}