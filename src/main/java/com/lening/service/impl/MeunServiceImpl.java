package com.lening.service.impl;

import com.lening.entity.MeunBean;
import com.lening.entity.MeunBeanExample;
import com.lening.mapper.MeunMapper;
import com.lening.service.MeunService;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeunServiceImpl implements MeunService {

    @Resource
    private MeunMapper meunMapper;


    @Override
    public List<MeunBean> getMeunListByPid(Long pid) {
        MeunBeanExample example = new MeunBeanExample();
        MeunBeanExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);
        return meunMapper.selectByExample(example);
    }

    @Override
    public void saveMeun(MeunBean meunBean) {
        if(meunBean!=null){
            if(meunBean.getId()!=null){
                meunMapper.updateByPrimaryKeySelective(meunBean);
            }else{
                meunMapper.insertSelective(meunBean);
            }
        }
    }

    List<Long> ids = new ArrayList<Long>();
    @Override
    public void deleteMeunById(Long id) {
        getMeunListByPidToDelete(id);
        for (Long id1 : ids) {
            meunMapper.deleteByPrimaryKey(id1);
        }
    }

    private void getMeunListByPidToDelete(Long pid) {
        ids.add(pid);
        MeunBeanExample example = new MeunBeanExample();
        MeunBeanExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);
        List<MeunBean> list = meunMapper.selectByExample(example);
        if(list!=null&&list.size()>=1){
            for (MeunBean bean : list) {
                getMeunListByPidToDelete(bean.getId());
            }
        }

    }

}
