package com.ruoyi.cms.blog.mapper;

import com.ruoyi.cms.blog.domain.CmsBlogBib;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * blog文献
 Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-12
 */
@Mapper
public interface CmsBlogBibMapper
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
     * 新增blog文献

     *
     * @param cmsBlogBib blog文献

     * @return 结果
     */
    public int insertCmsBlogBib(CmsBlogBib cmsBlogBib);


    /**
     * 批量添加文献
     * **/
    public int batchBlogBib(List<CmsBlogBib> blogBibList);

    /**
     * 修改blog文献

     *
     * @param cmsBlogBib blog文献

     * @return 结果
     */
    public int updateCmsBlogBib(CmsBlogBib cmsBlogBib);

    /**
     * 删除blog文献

     *
     * @param blogId blog文献
    主键
     * @return 结果
     */
    public int deleteCmsBlogBibByBlogId(Long blogId);

    /**
     * 批量删除blog文献

     *
     * @param blogIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmsBlogBibByBlogIds(Long[] blogIds);
}