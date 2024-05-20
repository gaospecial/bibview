package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ZoteroMapper;
import com.ruoyi.system.domain.Zotero;
import com.ruoyi.system.service.IZoteroService;

/**
 * 文献配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
@Service
public class ZoteroServiceImpl implements IZoteroService 
{
    @Autowired
    private ZoteroMapper zoteroMapper;

    /**
     * 查询文献配置
     * 
     * @param key 文献配置主键
     * @return 文献配置
     */
    @Override
    public Zotero selectZoteroById(String key)
    {
        return zoteroMapper.selectZoteroById(key);
    }

    /**
     * 查询文献配置列表
     * 
     * @param zotero 文献配置
     * @return 文献配置
     */
    @Override
    public List<Zotero> selectZoteroList(Zotero zotero)
    {
        return zoteroMapper.selectZoteroList(zotero);
    }

    /**
     * 新增文献配置
     * 
     * @param zotero 文献配置
     * @return 结果
     */
    @Override
    public int insertZotero(Zotero zotero)
    {
        return zoteroMapper.insertZotero(zotero);
    }

    /**
     * 修改文献配置
     * 
     * @param zotero 文献配置
     * @return 结果
     */
    @Override
    public int updateZotero(Zotero zotero)
    {
        return zoteroMapper.updateZotero(zotero);
    }

    /**
     * 批量删除文献配置
     * 
     * @param ids 需要删除的文献配置主键
     * @return 结果
     */
    @Override
    public int deleteZoteroByIds(String[] ids)
    {
        return zoteroMapper.deleteZoteroByIds(ids);
    }

    /**
     * 删除文献配置信息
     * 
     * @param id 文献配置主键
     * @return 结果
     */
    @Override
    public int deleteZoteroByIds(String id)
    {
        return zoteroMapper.deleteZoteroById(id);
    }
}
