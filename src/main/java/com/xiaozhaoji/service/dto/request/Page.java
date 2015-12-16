package com.xiaozhaoji.service.dto.request;

public class Page {

    private final static int DEFAULT_PAGE_ELEMENT_CNT = 10;
    /**
     * 页数从1开始
     */
    private int curPage;

    private int pageElementCount = 10;

    /**
     * 条目数
     * 
     */
    private int totalElementCount;

    public int getPageElementCount() {
        return pageElementCount;
    }

    public void setPageElementCount(int pageElementCount) {
        this.pageElementCount = pageElementCount;
    }

    public Page() {
        totalElementCount = 0;
        curPage = 1;
        this.pageElementCount = DEFAULT_PAGE_ELEMENT_CNT;
    }

    public Page(int pageEleCnt) {
        this.pageElementCount = pageEleCnt;
    }

    public String toString() {
        return getFirstNumber() + ":" + getCurPageElementCount() + " curPage = " + getCurPage() + " totalPates = "
            + getTotalPages();
    }

    /**
     * 从0开始
     */
    public int getFirstNumber() {
        return (curPage - 1) * pageElementCount;
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

        return this.getTotalElementCount() / pageElementCount
            + (this.getTotalElementCount() % pageElementCount != 0 ? 1 : 0);
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
