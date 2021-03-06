package hello.service;

import hello.dao.BlogDao;
import hello.entity.Blog;
import hello.entity.BlogResult;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * creat by nickless
 *
 * @Date 2019/10/28 21:45
 */
@Service
public class BlogService {
    private BlogDao blogDao;
    private UserService userService;

    @Inject
    public BlogService(BlogDao blogDao, UserService userService) {
        this.blogDao = blogDao;
        this.userService = userService;
    }

    public BlogResult getBlogs(Integer page, Integer pageSize, Integer userId) {
        try {
            List<Blog> blogs = blogDao.getBlogs(page, pageSize, userId);

            blogs.forEach(blog -> blog.setUser(userService.getUserById(blog.getUserId())));
            int count = blogDao.count(userId);

            int pageCout = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;

            return BlogResult.newResults(blogs, count, page, pageCout);
        } catch (Exception e) {

            return BlogResult.failure("系统异常");
        }
    }

}
