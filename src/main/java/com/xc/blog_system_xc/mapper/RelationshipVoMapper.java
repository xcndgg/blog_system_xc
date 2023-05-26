package com.xc.blog_system_xc.mapper;
import com.xc.blog_system_xc.domain.RelationshipVoExample;
import com.xc.blog_system_xc.domain.RelationshipVoKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Mapper
public interface RelationshipVoMapper {


    @Delete("DELETE FROM t_relationships WHERE mid = #{mid}")
    void deleteByExample(Integer mid);

    List<RelationshipVoKey> selectByExample(RelationshipVoExample relationshipVoExample);
}