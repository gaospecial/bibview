package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文献配置对象 zotero
 *
 * @author ruoyi
 * @date 2024-05-08
 */
public class Zotero extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** zoter Api key */
    @Excel(name = "zoter Api key")
    private String key;

    /** $column.columnComment */
    private Long id;

    /** Zotero user or group  */
    @Excel(name = "Zotero user or group ")
    private String group;

    /** User or Group ID */
    @Excel(name = "User or Group ID")
    private Long groupid;

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getKey()
    {
        return key;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setGroup(String group)
    {
        this.group = group;
    }

    public String getGroup()
    {
        return group;
    }
    public void setGroupid(Long groupid)
    {
        this.groupid = groupid;
    }

    public Long getGroupid()
    {
        return groupid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("key", getKey())
                .append("id", getId())
                .append("group", getGroup())
                .append("groupid", getGroupid())
                .toString();
    }
}
