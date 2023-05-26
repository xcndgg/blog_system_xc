package com.xc.blog_system_xc.service.impl;

import com.xc.blog_system_xc.domain.RelationshipVoExample;
import com.xc.blog_system_xc.domain.RelationshipVoKey;
import com.xc.blog_system_xc.mapper.RelationshipVoMapper;
import com.xc.blog_system_xc.service.RelationshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by BlueT on 2017/3/18.
 */
@Service
public class RelationshipServiceImpl implements RelationshipService {


    @Resource
    private RelationshipVoMapper relationshipVoMapper;

    @Override
    public void deleteById(Integer cid, Integer mid) {
        relationshipVoMapper.deleteByExample(mid);
    }



    @Override
    public List<RelationshipVoKey> getRelationshipById(Integer cid, Integer mid) {
        RelationshipVoExample relationshipVoExample = new RelationshipVoExample();
        RelationshipVoExample.Criteria criteria = relationshipVoExample.createCriteria();
        if (cid != null) {
            criteria.andCidEqualTo(cid);
        }
        if (mid != null) {
            criteria.andMidEqualTo(mid);
        }
        return relationshipVoMapper.selectByExample(relationshipVoExample);
    }
}
