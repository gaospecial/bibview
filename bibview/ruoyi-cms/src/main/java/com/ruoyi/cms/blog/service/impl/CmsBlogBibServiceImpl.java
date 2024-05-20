package com.ruoyi.cms.blog.service.impl;


import com.ruoyi.cms.blog.domain.CmsBlogBib;
import com.ruoyi.cms.blog.mapper.CmsBlogBibMapper;
import com.ruoyi.cms.blog.service.ICmsBlogBibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * blog文献
 Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-12
 */
@Service
public class CmsBlogBibServiceImpl implements ICmsBlogBibService
{
    @Autowired
    private CmsBlogBibMapper cmsBlogBibMapper;

    /**
     * 查询blog文献

     *
     * @param blogId blog文献
    主键
     * @return blog文献

     */
    @Override
    public CmsBlogBib selectCmsBlogBibByBlogId(Long blogId)
    {
        return cmsBlogBibMapper.selectCmsBlogBibByBlogId(blogId);
    }

    /**
     * 查询blog文献
     列表
     *
     * @param cmsBlogBib blog文献

     * @return blog文献

     */
    @Override
    public List<CmsBlogBib> selectCmsBlogBibList(CmsBlogBib cmsBlogBib)
    {
        return cmsBlogBibMapper.selectCmsBlogBibList(cmsBlogBib);
    }

    /**
     * 批量关联文献
     * **/
    @Override
    public int batchBlogBib(List<CmsBlogBib> cmsBlogBibList) {
        return cmsBlogBibMapper.batchBlogBib(cmsBlogBibList);
    }

    /**
     * 新增blog文献

     *
     * @param cmsBlogBib blog文献

     * @return 结果
     */
    @Override
    public int insertCmsBlogBib(CmsBlogBib cmsBlogBib)
    {
        return cmsBlogBibMapper.insertCmsBlogBib(cmsBlogBib);
    }

    /**
     * 修改blog文献

     *
     * @param cmsBlogBib blog文献

     * @return 结果
     */
    @Override
    public int updateCmsBlogBib(CmsBlogBib cmsBlogBib)
    {
        return cmsBlogBibMapper.updateCmsBlogBib(cmsBlogBib);
    }

    /**
     * 批量删除blog文献

     *
     * @param blogIds 需要删除的blog文献
    主键
     * @return 结果
     */
    @Override
    public int deleteCmsBlogBibByBlogIds(Long[] blogIds)
    {
        return cmsBlogBibMapper.deleteCmsBlogBibByBlogIds(blogIds);
    }

    /**
     * 删除blog文献
     信息
     *
     * @param blogId blog文献
    主键
     * @return 结果
     */
    @Override
    public int deleteCmsBlogBibByBlogId(Long blogId)
    {
        return cmsBlogBibMapper.deleteCmsBlogBibByBlogId(blogId);
    }
}