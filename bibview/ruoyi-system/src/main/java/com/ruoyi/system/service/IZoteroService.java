package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Zotero;

/**
 * 文献配置Service接口
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
public interface IZoteroService 
{
    /**
     * 查询文献配置
     * 
     * @param id 文献配置主键
     * @return 文献配置
     */
    public Zotero selectZoteroById(String id);

    /**
     * 查询文献配置列表
     * 
     * @param zotero 文献配置
     * @return 文献配置集合
     */
    public List<Zotero> selectZoteroList(Zotero zotero);

    /**
     * 新增文献配置
     * 
     * @param zotero 文献配置
     * @return 结果
     */
    public int insertZotero(Zotero zotero);

    /**
     * 修改文献配置
     * 
     * @param zotero 文献配置
     * @return 结果
     */
    public int updateZotero(Zotero zotero);

    /**
     * 批量删除文献配置
     * 
     * @param ids 需要删除的文献配置主键集合
     * @return 结果
     */
    public int deleteZoteroByIds(String[] ids);

    /**
     * 删除文献配置信息
     * 
     * @param id 文献配置主键
     * @return 结果
     */
    public int deleteZoteroByIds(String id);
}
