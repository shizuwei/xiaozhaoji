package com.xiaozhaoji.service.dto.request;

public class PageDto {
    /**
     * 页数从1开始
     */
    private int curPage;
    private int totalPages;

    /**
     * 条目数
     * 
     */
    private int totalElementCount;
    private final static int DEFAULT_PAGE_ELEMENT_CNT = 20;

    /**
     * 从0开始
     */
    public int getFirstNumber() {
        return (curPage - 1) * DEFAULT_PAGE_ELEMENT_CNT;
    }

    /**
     * 从1开始
     * 
     * @return
     */
    public int getCurPage() {

        return curPage;
    }

    public void setCurPage(Integer curPage) {

        if (curPage == null) {
            this.curPage = 1;
            return;
        }
        this.curPage = curPage;
    }

    public int getTotalPages() {

        return totalPages;
    }

    public void setTotalPages(int totalPages) {

        this.totalPages = totalPages;
    }

    /**
     * 当前页条目数
     * 
     * @return
     */
    public int getCurPageElementCount() {

        return totalPages - getFirstNumber();
    }

    public int getTotalElementCount() {

        return totalElementCount;
    }

    public void setTotalElementCount(int totalElementCount) {

        this.totalElementCount = totalElementCount;
    }

}
