package com.ruoyi.system.service;

import java.io.IOException;
import java.util.List;
import com.ruoyi.system.domain.Bib;

/**
 * 文献管理Service接口
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
public interface IBibService 
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
     * 批量删除文献管理
     * 
     * @param ids 需要删除的文献管理主键集合
     * @return 结果
     */
    public int deleteBibByIds(Long[] ids);

    /**
     * 删除文献管理信息
     * 
     * @param id 文献管理主键
     * @return 结果
     */
    public int deleteBibById(Long id);



    /**
     * 同步文献
     * **/
    public void syncBib() throws IOException;

}
