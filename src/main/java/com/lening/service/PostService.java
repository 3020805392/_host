package com.lening.service;

import com.lening.entity.PostBean;
import com.lening.utils.Page;

public interface PostService {
    Page<PostBean> getPostListConn(PostBean postBean,Integer pageNum,Integer pageSize);
}
