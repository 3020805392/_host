package com.lening.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lening.entity.MeunBean;
import com.lening.entity.UserBean;
import com.lening.entity.UserBeanExample;
import com.lening.mapper.MeunMapper;
import com.lening.mapper.UserMapper;
import com.lening.service.UserService;
import com.lening.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MeunMapper meunMapper;


    @Override
    public List<UserBean> getUserList() {
        return userMapper.selectByExample(null);
    }

    @Override
    public Page<UserBean> getUserListConn(UserBean ub, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        UserBeanExample example = new UserBeanExample();
        UserBeanExample.Criteria criteria = example.createCriteria();
        if(ub!=null){
            //按照用户名模糊，
            if(ub.getUname()!=null&&ub.getUname().length()>=1){
                criteria.andUnameLike("%"+ub.getUname()+"%");
            }
            if(ub.getAge()!=null){
                criteria.andAgeGreaterThanOrEqualTo(ub.getAge());
            }
            if(ub.getEage()!=null){
                criteria.andAgeLessThanOrEqualTo(ub.getEage());
            }
        }
        List<UserBean> list = userMapper.selectByExample(example);
        PageInfo<UserBean> pageInfo = new PageInfo<UserBean>(list);
        Long total = pageInfo.getTotal();
        Page page = new Page(pageInfo.getPageNum()+"",total.intValue(),pageInfo.getPageSize()+"");
        page.setList(list);
        return page;
    }

    @Override
    public List<MeunBean> getMeunList() {
        return meunMapper.selectByExample(null);
    }

    public List<MeunBean> getMeunList2(){
        Long[] ids = {1L,2L,3L};
        List<MeunBean> list = meunMapper.selectByExample(null);
        for (Long id : ids){
            for (MeunBean bean : list){
                if (id.equals(bean.getId())){
                    bean.setChecked(true);
                }
            }
        }
        return list;
    }


}
