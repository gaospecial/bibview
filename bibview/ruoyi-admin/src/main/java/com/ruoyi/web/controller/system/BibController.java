package com.ruoyi.web.controller.system;

import java.io.IOException;
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
import com.ruoyi.system.domain.Bib;
import com.ruoyi.system.service.IBibService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文献管理Controller
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/system/bib")
public class BibController extends BaseController
{
    @Autowired
    private IBibService bibService;

    /**
     * 查询文献管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:bib:list')")
    @GetMapping("/list")
    public TableDataInfo list(Bib bib)
    {
        startPage();
        List<Bib> list = bibService.selectBibList(bib);
        return getDataTable(list);
    }

    /**
     * 导出文献管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:bib:export')")
    @Log(title = "文献管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Bib bib)
    {
        List<Bib> list = bibService.selectBibList(bib);
        ExcelUtil<Bib> util = new ExcelUtil<Bib>(Bib.class);
        util.exportExcel(response, list, "文献管理数据");
    }

    /**
     * 获取文献管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:bib:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bibService.selectBibById(id));
    }

    /**
     * 新增文献管理
     */
    @PreAuthorize("@ss.hasPermi('system:bib:add')")
    @Log(title = "文献管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Bib bib)
    {
        return toAjax(bibService.insertBib(bib));
    }

    /**
     * 修改文献管理
     */
    @PreAuthorize("@ss.hasPermi('system:bib:edit')")
    @Log(title = "文献管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Bib bib)
    {
        return toAjax(bibService.updateBib(bib));
    }

    /**
     * 删除文献管理
     */
    @PreAuthorize("@ss.hasPermi('system:bib:remove')")
    @Log(title = "文献管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bibService.deleteBibByIds(ids));
    }

    /**
     * 同步文献
     * **/
    @GetMapping("/syncBib")
    public  AjaxResult syncBib()
    {
        try {
            bibService.syncBib();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.success();
    }

}
