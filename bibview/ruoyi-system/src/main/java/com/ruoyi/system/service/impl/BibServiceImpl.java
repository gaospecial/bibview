package com.ruoyi.system.service.impl;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.ZoteroApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BibMapper;
import com.ruoyi.system.domain.Bib;
import com.ruoyi.system.service.IBibService;

import javax.annotation.Resource;

/**
 * 文献管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-08
 */
@Service
public class BibServiceImpl implements IBibService {
    @Resource
    private BibMapper bibMapper;



    public BibServiceImpl(BibMapper bibMapper) {
        this.bibMapper = bibMapper;
    }


    /**
     * 查询文献管理
     *
     * @param id 文献管理主键
     * @return 文献管理
     */
    @Override
    public Bib selectBibById(Long id) {
        return bibMapper.selectBibById(id);
    }

    /**
     * 查询文献管理列表
     *
     * @param bib 文献管理
     * @return 文献管理
     */
    @Override
    public List<Bib> selectBibList(Bib bib) {
        return bibMapper.selectBibList(bib);
    }

    /**
     * 新增文献管理
     *
     * @param bib 文献管理
     * @return 结果
     */
    @Override
    public int insertBib(Bib bib) {
        return bibMapper.insertBib(bib);
    }

    /**
     * 修改文献管理
     *
     * @param bib 文献管理
     * @return 结果
     */
    @Override
    public int updateBib(Bib bib) {
        return bibMapper.updateBib(bib);
    }

    /**
     * 批量删除文献管理
     *
     * @param ids 需要删除的文献管理主键
     * @return 结果
     */
    @Override
    public int deleteBibByIds(Long[] ids) {
        return bibMapper.deleteBibByIds(ids);
    }

    /**
     * 删除文献管理信息
     *
     * @param id 文献管理主键
     * @return 结果
     */
    @Override
    public int deleteBibById(Long id) {
        return bibMapper.deleteBibById(id);
    }


    /**
     * 同步文献
     **/
    public void syncBib() throws IOException {
        bibMapper.deleteBibAll();

        bibMapper.resetAutoIncrement();

        //获取文献信息数组
        List<String> bibList = ZoteroApiClient.getNewContent("12704142", "9JPDZK49", "xFJCPkRvAZS3SbtZaM0taalD");

        for (String itemKey : bibList){
            //获取文献内容
            String content = ZoteroApiClient.getFulltext("12704142", itemKey, "xFJCPkRvAZS3SbtZaM0taalD");
            JSONObject result = ZoteroApiClient.getBriefInfo("12704142", itemKey, "xFJCPkRvAZS3SbtZaM0taalD");
            System.out.println(result.getString("title"));
            Bib bib = new Bib(result.getString("title"),
                    content,
                    result.getString("author"),
                    result.getString("abstractNote"),
                    result.getString("libraryCatalog"),
                    result.getString("issue"),
                    result.getString("issn"),
                    result.getString("doi"),
                    result.getString("key"),
                    result.getString("archiveLocation"),
                    result.getString("extra"),
                    result.getString("rights"),
                    result.getString("collections"),
                    result.getString("accessDate"),
                    result.getString("pages"),
                    result.getString("journalAbbreviation"),
                    result.getString("seriestitle"),
                    result.getString("publicationTitle"),
                    result.getString("version"),
                    result.getString("url"),
                    result.getString("dateAdded"),
                    result.getString("dateModified"),
                    result.getString("shorttitle"),
                    result.getString("language"),
                    result.getString("creators"),
                    result.getString("seriesText"),
                    result.getString("itemType"),
                    result.getString("date"),
                    result.getString("tags"),
                    result.getString("volume"),
                    result.getString("callNumber"),
                    result.getString("series"),
                    result.getString("relations"));
            System.out.println(bibMapper.insertBib(bib));
        }



    }




}
