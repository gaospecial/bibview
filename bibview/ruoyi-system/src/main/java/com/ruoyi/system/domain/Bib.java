package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文献管理对象 bib
 *
 * @author ruoyi
 * @String 2024-05-12
 */
public class Bib extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文献id */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 摘要  */
    @Excel(name = "摘要 ")
    private String abstractNote;

    /** 图书馆目录 */
    @Excel(name = "图书馆目录")
    private String libraryCatalog;

    /** 期数  */
    @Excel(name = "期数 ")
    private String issue;

    /** ISSN */
    @Excel(name = "ISSN")
    private String issn;

    /** DOI */
    @Excel(name = "DOI")
    private String doi;

    /** 关键词 */
    @Excel(name = "关键词")
    private String key;

    /** 存档位置 */
    @Excel(name = "存档位置")
    private String archiveLocation;

    /** 额外信息 */
    @Excel(name = "额外信息")
    private String extra;

    /** 版权 */
    @Excel(name = "版权")
    private String rights;

    /** 收藏 */
    @Excel(name = "收藏")
    private String collections;

    /** 访问日期 */
    private String accessDate;

    /** 页码 */
    @Excel(name = "页码")
    private String pages;

    /** 期刊缩写 */
    @Excel(name = "期刊缩写")
    private String journalAbbreviation;

    /** 简短标题 */
    @Excel(name = "简短标题")
    private String seriestitle;

    /** 出版物标题 */
    @Excel(name = "出版物标题")
    private String publicationTitle;

    /** 版本 */
    @Excel(name = "版本")
    private String version;

    /** 链接 */
    @Excel(name = "链接")
    private String url;

    /** 访问日期 */
    private String dateAdded;

    /** 修改日期  */
    private String dateModified;

    /** 简短标题 */
    @Excel(name = "简短标题")
    private String shorttitle;

    /** 语言 */
    @Excel(name = "语言")
    private String language;

    /** 创建者  */
    @Excel(name = "创建者 ")
    private String creators;

    /** 系列文字 */
    @Excel(name = "系列文字")
    private String seriesText;

    /** 文章类型 */
    @Excel(name = "文章类型")
    private String itemType;

    /** 发表日期 */
    private String date;

    /** 标签 */
    @Excel(name = "标签")
    private String tags;

    /** 卷 */
    @Excel(name = "卷")
    private String volume;

    /** 索书号 */
    @Excel(name = "索书号")
    private String callNumber;

    /** 系列 */
    @Excel(name = "系列")
    private String series;

    /** 关系 */
    @Excel(name = "关系")
    private String relations;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getAuthor()
    {
        return author;
    }
    public void setAbstractNote(String abstractNote)
    {
        this.abstractNote = abstractNote;
    }

    public String getAbstractNote()
    {
        return abstractNote;
    }
    public void setLibraryCatalog(String libraryCatalog)
    {
        this.libraryCatalog = libraryCatalog;
    }

    public String getLibraryCatalog()
    {
        return libraryCatalog;
    }
    public void setIssue(String issue)
    {
        this.issue = issue;
    }

    public String getIssue()
    {
        return issue;
    }
    public void setIssn(String issn)
    {
        this.issn = issn;
    }

    public String getIssn()
    {
        return issn;
    }
    public void setDoi(String doi)
    {
        this.doi = doi;
    }

    public String getDoi()
    {
        return doi;
    }
    public void setKey(String key)
    {
        this.key = key;
    }

    public String getKey()
    {
        return key;
    }
    public void setArchiveLocation(String archiveLocation)
    {
        this.archiveLocation = archiveLocation;
    }

    public String getArchiveLocation()
    {
        return archiveLocation;
    }
    public void setExtra(String extra)
    {
        this.extra = extra;
    }

    public String getExtra()
    {
        return extra;
    }
    public void setRights(String rights)
    {
        this.rights = rights;
    }

    public String getRights()
    {
        return rights;
    }
    public void setCollections(String collections)
    {
        this.collections = collections;
    }

    public String getCollections()
    {
        return collections;
    }
    public void setAccessDate(String accessDate)
    {
        this.accessDate = accessDate;
    }

    public String getAccessDate()
    {
        return accessDate;
    }
    public void setPages(String pages)
    {
        this.pages = pages;
    }

    public String getPages()
    {
        return pages;
    }
    public void setJournalAbbreviation(String journalAbbreviation)
    {
        this.journalAbbreviation = journalAbbreviation;
    }

    public String getJournalAbbreviation()
    {
        return journalAbbreviation;
    }
    public void setSeriestitle(String seriestitle)
    {
        this.seriestitle = seriestitle;
    }

    public String getSeriestitle()
    {
        return seriestitle;
    }
    public void setPublicationTitle(String publicationTitle)
    {
        this.publicationTitle = publicationTitle;
    }

    public String getPublicationTitle()
    {
        return publicationTitle;
    }
    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getVersion()
    {
        return version;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setDateAdded(String dateAdded)
    {
        this.dateAdded = dateAdded;
    }

    public String getDateAdded()
    {
        return dateAdded;
    }
    public void setDateModified(String dateModified)
    {
        this.dateModified = dateModified;
    }

    public String getDateModified()
    {
        return dateModified;
    }
    public void setShorttitle(String shorttitle)
    {
        this.shorttitle = shorttitle;
    }

    public String getShorttitle()
    {
        return shorttitle;
    }
    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getLanguage()
    {
        return language;
    }
    public void setCreators(String creators)
    {
        this.creators = creators;
    }

    public String getCreators()
    {
        return creators;
    }
    public void setSeriesText(String seriesText)
    {
        this.seriesText = seriesText;
    }

    public String getSeriesText()
    {
        return seriesText;
    }
    public void setItemType(String itemType)
    {
        this.itemType = itemType;
    }

    public String getItemType()
    {
        return itemType;
    }
    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDate()
    {
        return date;
    }
    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public String getTags()
    {
        return tags;
    }
    public void setVolume(String volume)
    {
        this.volume = volume;
    }

    public String getVolume()
    {
        return volume;
    }
    public void setCallNumber(String callNumber)
    {
        this.callNumber = callNumber;
    }

    public String getCallNumber()
    {
        return callNumber;
    }
    public void setSeries(String series)
    {
        this.series = series;
    }

    public String getSeries()
    {
        return series;
    }
    public void setRelations(String relations)
    {
        this.relations = relations;
    }

    public String getRelations()
    {
        return relations;
    }

    public Bib(){}
    public Bib(String title, String content, String author, String abstractNote, String libraryCatalog, String issue, String issn, String doi, String key, String archiveLocation, String extra, String rights, String collections, String accessDate, String pages, String journalAbbreviation, String seriestitle, String publicationTitle, String version, String url, String dateAdded, String dateModified, String shorttitle, String language, String creators, String seriesText, String itemType, String date, String tags, String volume, String callNumber, String series, String relations) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.abstractNote = abstractNote;
        this.libraryCatalog = libraryCatalog;
        this.issue = issue;
        this.issn = issn;
        this.doi = doi;
        this.key = key;
        this.archiveLocation = archiveLocation;
        this.extra = extra;
        this.rights = rights;
        this.collections = collections;
        this.accessDate = accessDate;
        this.pages = pages;
        this.journalAbbreviation = journalAbbreviation;
        this.seriestitle = seriestitle;
        this.publicationTitle = publicationTitle;
        this.version = version;
        this.url = url;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.shorttitle = shorttitle;
        this.language = language;
        this.creators = creators;
        this.seriesText = seriesText;
        this.itemType = itemType;
        this.date = date;
        this.tags = tags;
        this.volume = volume;
        this.callNumber = callNumber;
        this.series = series;
        this.relations = relations;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("content", getContent())
                .append("author", getAuthor())
                .append("abstractNote", getAbstractNote())
                .append("libraryCatalog", getLibraryCatalog())
                .append("issue", getIssue())
                .append("issn", getIssn())
                .append("doi", getDoi())
                .append("key", getKey())
                .append("archiveLocation", getArchiveLocation())
                .append("extra", getExtra())
                .append("rights", getRights())
                .append("collections", getCollections())
                .append("accessDate", getAccessDate())
                .append("pages", getPages())
                .append("journalAbbreviation", getJournalAbbreviation())
                .append("seriestitle", getSeriestitle())
                .append("publicationTitle", getPublicationTitle())
                .append("version", getVersion())
                .append("url", getUrl())
                .append("dateAdded", getDateAdded())
                .append("dateModified", getDateModified())
                .append("shorttitle", getShorttitle())
                .append("language", getLanguage())
                .append("creators", getCreators())
                .append("seriesText", getSeriesText())
                .append("itemType", getItemType())
                .append("String", getDate())
                .append("tags", getTags())
                .append("volume", getVolume())
                .append("callNumber", getCallNumber())
                .append("series", getSeries())
                .append("relations", getRelations())
                .toString();
    }
}
