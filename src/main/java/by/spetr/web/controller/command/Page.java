package by.spetr.web.controller.command;

public class Page {
    public static final int PAGE_SIZE = 12;
    private int total;
    private int currentPage;
    private int size;

    public Page(int total, int currentPage, int size) {
        this.total = total;
        this.currentPage = currentPage;
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isFirstPage() {
        return currentPage == 1;
    }

    public boolean isLastPage() {
        return currentPage == pageCount();
    }

    public int pageCount() {
        return (int) Math.ceil((double) total / size);
    }
}
