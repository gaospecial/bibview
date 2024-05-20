package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Bib;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文献管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
@Mapper
public interface BibMapper 
{
    /**
     * 查询文献管理
     * 
     * @param id 文献管理主键
     * @return 文献管理
     */
    public Bib selectBibById(Long id);

    /**
     * 查询文献管理列表
     * 
     * @param bib 文献管理
     * @return 文献管理集合
     */
    public List<Bib> selectBibList(Bib bib);

    /**
     * 新增文献管理
     * 
     * @param bib 文献管理
     * @return 结果
     */
    public int insertBib(Bib bib);

    /**
     * 修改文献管理
     * 
     * @param bib 文献管理
     * @return 结果
     */
    public int updateBib(Bib bib);


    /**
     * 删除全部文献管理
     * **/
    public int deleteBibAll();

    /**
     * 删除文献管理
     * 
     * @param id 文献管理主键
     * @return 结果
     */
    public int deleteBibById(Long id);

    /**
     * 批量删除文献管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBibByIds(Long[] ids);


    /**
     * 索引重置
     * **/
    public int resetAutoIncrement();
}
