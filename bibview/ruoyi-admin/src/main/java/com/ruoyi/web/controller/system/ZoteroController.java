package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Zotero;
import com.ruoyi.system.service.IZoteroService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文献配置Controller
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/system/zotero")
public class ZoteroController extends BaseController
{
    @Autowired
    private IZoteroService zoteroService;

    /**
     * 查询文献配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:zotero:list')")
    @GetMapping("/list")
    public TableDataInfo list(Zotero zotero)
    {
        startPage();
        List<Zotero> list = zoteroService.selectZoteroList(zotero);
        return getDataTable(list);
    }

    /**
     * 导出文献配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:zotero:export')")
    @Log(title = "文献配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Zotero zotero)
    {
        List<Zotero> list = zoteroService.selectZoteroList(zotero);
        ExcelUtil<Zotero> util = new ExcelUtil<Zotero>(Zotero.class);
        util.exportExcel(response, list, "文献配置数据");
    }

    /**
     * 获取文献配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:zotero:query')")
    @GetMapping(value = "/{key}")
    public AjaxResult getInfo(@PathVariable("key") String key)
    {
        return AjaxResult.success(zoteroService.selectZoteroById(key));
    }

    /**
     * 新增文献配置
     */
    @PreAuthorize("@ss.hasPermi('system:zotero:add')")
    @Log(title = "文献配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Zotero zotero)
    {
        return toAjax(zoteroService.insertZotero(zotero));
    }

    /**
     * 修改文献配置
     */
    @PreAuthorize("@ss.hasPermi('system:zotero:edit')")
    @Log(title = "文献配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Zotero zotero)
    {
        return toAjax(zoteroService.updateZotero(zotero));
    }

    /**
     * 删除文献配置
     */
    @PreAuthorize("@ss.hasPermi('system:zotero:remove')")
    @Log(title = "文献配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{keys}")
    public AjaxResult remove(@PathVariable String[] keys)
    {
        return toAjax(zoteroService.deleteZoteroByIds(keys));
    }
}
