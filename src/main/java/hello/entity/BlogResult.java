package hello.entity;

import java.util.List;

/**
 * creat by nickless
 *
 * @Date 2019/11/3 9:07
 */
public class BlogResult extends Result<List<Blog>> {
    private int total;
    private int page;
    private int toatlPage;

    public static BlogResult newResults(List<Blog> data, int page, int total, int toatlPage) {
        return new BlogResult("ok", "获取成功", data, page, total, toatlPage);
    }


    private BlogResult(String status, String msg, List<Blog> data, int page, int total, int toatlPage) {
        super(status, msg, data);
        this.page = page;
        this.toatlPage = toatlPage;
        this.total = total;
    }

    public static BlogResult failure(String message) {
        return new BlogResult("fail", message, null, 0, 0, 0);
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getToatlPage() {
        return toatlPage;
    }
}
