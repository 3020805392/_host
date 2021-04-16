package com.lening.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lening.entity.DeptBean;
import com.lening.entity.DeptBeanExample;
import com.lening.mapper.DeptMapper;
import com.lening.service.DeptService;
import com.lening.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Page<DeptBean> getDeptListConn(DeptBean deptBean, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        DeptBeanExample example = new DeptBeanExample();
        DeptBeanExample.Criteria criteria = example.createCriteria();
        if (deptBean !=null ){
            if (deptBean.getDeptname()!=null&&deptBean.getDeptname().length()>=1){
                criteria.andDeptnameLike("%"+deptBean.getDeptname()+"%");
            }
        }
        List<DeptBean> deptBeans = deptMapper.selectByExample(example);
        PageInfo<Object> pageInfo = new PageInfo(deptBeans);
        Long total = pageInfo.getTotal();
        Page page = new Page(""+pageInfo.getPageNum(),total.intValue(),pageInfo.getPageSize()+"");
        page.setList(deptBeans);
        return page;
    }
}
