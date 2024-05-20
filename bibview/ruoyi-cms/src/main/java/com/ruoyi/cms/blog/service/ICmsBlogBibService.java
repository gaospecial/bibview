package com.ruoyi.cms.blog.service;

import com.ruoyi.cms.blog.domain.CmsBlogBib;

import java.util.List;

/**
 * blog文献
 Service接口
 *
 * @author ruoyi
 * @date 2024-05-12
 */
public interface ICmsBlogBibService
{
    /**
     * 查询blog文献

     *
     * @param blogId blog文献
    主键
     * @return blog文献

     */
    public CmsBlogBib selectCmsBlogBibByBlogId(Long blogId);

    /**
     * 查询blog文献
     列表
     *
     * @param cmsBlogBib blog文献

     * @return blog文献
    集合
     */
    public List<CmsBlogBib> selectCmsBlogBibList(CmsBlogBib cmsBlogBib);

    /**
     * 批量新增文献
     * **/
    public int batchBlogBib(List<CmsBlogBib> cmsBlogBibList);


    /**
     * 新增blog文献
     *
     * @param cmsBlogBib blog文献

     * @return 结果
     */
    public int insertCmsBlogBib(CmsBlogBib cmsBlogBib);

    /**
     * 修改blog文献

     *
     * @param cmsBlogBib blog文献

     * @return 结果
     */
    public int updateCmsBlogBib(CmsBlogBib cmsBlogBib);

    /**
     * 批量删除blog文献

     *
     * @param blogIds 需要删除的blog文献
    主键集合
     * @return 结果
     */
    public int deleteCmsBlogBibByBlogIds(Long[] blogIds);

    /**
     * 删除blog文献
     信息
     *
     * @param blogId blog文献
    主键
     * @return 结果
     */
    public int deleteCmsBlogBibByBlogId(Long blogId);
}