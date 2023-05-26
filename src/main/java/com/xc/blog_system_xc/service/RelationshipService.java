package com.xc.blog_system_xc.service;


import com.xc.blog_system_xc.domain.RelationshipVoKey;

import java.util.List;


public interface RelationshipService {

    void deleteById(Integer cid, Integer mid);

    List<RelationshipVoKey> getRelationshipById(Integer cid, Integer mid);
}
