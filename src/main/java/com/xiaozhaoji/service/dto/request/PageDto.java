package com.xiaozhaoji.service.dto.request;

public class PageDto {
    /**
     * 页数从1开始
     */
    private int curPage;

    /**
     * 条目数
     * 
     */
    private int totalElementCount;
    private final static int DEFAULT_PAGE_ELEMENT_CNT = 10;

    public int getPageElementCount() {
        return DEFAULT_PAGE_ELEMENT_CNT;
    }

    public PageDto() {
        totalElementCount = 0;
        curPage = 1;
    }

    public String toString() {
        return getFirstNumber() + ":" + getCurPageElementCount() + " curPage = " + getCurPage() + " totalPates = "
            + getTotalPages();
    }

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

        return this.getTotalElementCount() / DEFAULT_PAGE_ELEMENT_CNT
            + (this.getTotalElementCount() % DEFAULT_PAGE_ELEMENT_CNT != 0 ? 1 : 0);
    }

    /**
     * 当前页条目数
     * 
     * @return
     */
    public int getCurPageElementCount() {
        // 剩下的记录总数
        int leftEleCnt = getTotalElementCount() - getFirstNumber();
        if (leftEleCnt >= getPageElementCount()) {
            return getPageElementCount();
        }
        return leftEleCnt % getPageElementCount();
    }

    public int getTotalElementCount() {

        return totalElementCount;
    }

    public void setTotalElementCount(int totalElementCount) {

        this.totalElementCount = totalElementCount;
    }

}
