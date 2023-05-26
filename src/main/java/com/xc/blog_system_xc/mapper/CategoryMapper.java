package com.xc.blog_system_xc.mapper;

import com.xc.blog_system_xc.domain.MetaVo;
import com.xc.blog_system_xc.domain.MetaVoExample;
import com.xc.blog_system_xc.domain.TagAndCategory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select a.*, count(b.cid) as count from t_metas a left join `t_relationships` b on a.mid = b.mid where a.type = #{type} group by a.mid limit 10")
    List<TagAndCategory> selectTagAndCategoryList(String type);

    List<TagAndCategory> selectByExample(MetaVoExample metaVoExample);

    MetaVo selectByPrimaryKey(Integer mid);

    void updateByPrimaryKeySelective(MetaVo metas);

    void insertSelective(MetaVo metas);
    @Delete("DELETE FROM t_metas WHERE mid = #{mid}")
    void deleteByPrimaryKey(int mid);
}
