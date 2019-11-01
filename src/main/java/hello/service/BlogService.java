package hello.service;

import hello.dao.BlogDao;
import hello.entity.Blog;
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

    @Inject
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public List<Blog> getBlogs(Integer page, Integer pageSize, Integer userId){
            return  blogDao.getBlogs(page,pageSize,userId);
    }

}
