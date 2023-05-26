package com.xc.blog_system_xc.service.impl;

import com.xc.blog_system_xc.domain.*;
import com.xc.blog_system_xc.mapper.CategoryMapper;
import com.xc.blog_system_xc.service.ArticleService;
import com.xc.blog_system_xc.service.CategoryService;
import com.xc.blog_system_xc.service.RelationshipService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private ArticleService articleService;
    @Resource
    private RelationshipService relationshipService;
    @Override
    public List<TagAndCategory> TagAndCategoryList(String type) {
        if (StringUtils.isNotBlank(type)) {

            return categoryMapper.selectTagAndCategoryList(type);
        } else {
            return null;
        }

    }

//    @Override
//    public void saveMeta(String type, String name, Integer mid) throws Exception {
//        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(name)) {
//            MetaVoExample metaVoExample = new MetaVoExample();
//            metaVoExample.createCriteria().andTypeEqualTo(type).andNameEqualTo(name);
//            List<TagAndCategory> metaVos = categoryMapper.selectByExample(metaVoExample);
//            MetaVo metas;
//            if (metaVos.size() != 0) {
//                throw new Exception("已经存在该项");
//            } else {
//                metas = new MetaVo();
//                metas.setName(name);
//                metas.setType(type);
//                categoryMapper.insertSelective(metas);
//                }
//            }
//        }
    @Override
    public void saveMeta(String type, String name, Integer mid) throws Exception {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(name)) {
            MetaVoExample metaVoExample = new MetaVoExample();
            metaVoExample.createCriteria().andTypeEqualTo(type).andNameEqualTo(name);
            List<TagAndCategory> metaVos = categoryMapper.selectByExample(metaVoExample);
            MetaVo metas;
            if (metaVos.size() != 0) {
                throw new Exception("已经存在该项");
            } else {
                metas = new MetaVo();
                metas.setName(name);
                if(null!=mid) {
                    MetaVo original = categoryMapper.selectByPrimaryKey(mid);
                    metas.setMid(mid);
                    categoryMapper.updateByPrimaryKeySelective(metas);
                    articleService.updateCategory(original.getName(),name);
                } else {
                    metas.setType(type);
                    categoryMapper.insertSelective(metas);
                }

            }
        }
    }


    @Override
    public void delete(int mid) {
        MetaVo metas = categoryMapper.selectByPrimaryKey(mid);
        if (null != metas) {
            String type = metas.getType();
            String name = metas.getName();

            categoryMapper.deleteByPrimaryKey(mid);

            List<RelationshipVoKey> rlist = relationshipService.getRelationshipById(null, mid);
            if (null != rlist) {
                for (RelationshipVoKey r : rlist) {
                    Article contents = articleService.getArticleDetail(r.getCid());
                    if (null != contents) {
                        Article temp = new Article();
                        temp.setId(r.getCid());
                        if (type.equals("category")) {
                            temp.setCategories(reMeta(name, contents.getCategories()));
                        }
                        if (type.equals("tag")) {
                            temp.setTags(reMeta(name, contents.getTags()));
                        }
                        articleService.updateContentByCid(temp);
                    }
                }
            }
            relationshipService.deleteById(null, mid);
        }
    }
    private String reMeta(String name, String metas) {
        String[] ms = StringUtils.split(metas, ",");
        StringBuilder sbuf = new StringBuilder();
        for (String m : ms) {
            if (!name.equals(m)) {
                sbuf.append(",").append(m);
            }
        }
        if (sbuf.length() > 0) {
            return sbuf.substring(1);
        }
        return "";
    }
}
