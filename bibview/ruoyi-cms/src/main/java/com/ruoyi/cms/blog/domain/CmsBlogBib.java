package com.ruoyi.cms.blog.domain;


import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * blog文献
 对象 cms_blog_bib
 *
 * @author ruoyi
 * @date 2024-05-12
 */
public class CmsBlogBib extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 博客ID */
    private Long blogId;

    /** 文献ID */
    private Long bibId;

    public void setBlogId(Long blogId)
    {
        this.blogId = blogId;
    }

    public Long getBlogId()
    {
        return blogId;
    }
    public void setBibId(Long bibId)
    {
        this.bibId = bibId;
    }

    public Long getBibId()
    {
        return bibId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("blogId", getBlogId())
                .append("bibId", getBibId())
                .toString();
    }
}