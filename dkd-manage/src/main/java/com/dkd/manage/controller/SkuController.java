package com.dkd.manage.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.dkd.common.annotation.Excel;
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
import com.dkd.common.annotation.Log;
import com.dkd.common.core.controller.BaseController;
import com.dkd.common.core.domain.AjaxResult;
import com.dkd.common.enums.BusinessType;
import com.dkd.manage.domain.Sku;
import com.dkd.manage.service.ISkuService;
import com.dkd.common.utils.poi.ExcelUtil;
import com.dkd.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.dkd.framework.datasource.DynamicDataSourceContextHolder.log;

/**
 * 商品管理Controller
 * 
 * @author charcoalfire
 * @date 2025-07-30
 */
@RestController
@RequestMapping("/manage/sku")
public class SkuController extends BaseController
{
    @Autowired
    private ISkuService skuService;

    /**
     * 查询商品管理列表
     */
    @PreAuthorize("@ss.hasPermi('manage:sku:list')")
    @GetMapping("/list")
    public TableDataInfo list(Sku sku)
    {
        startPage();
        List<Sku> list = skuService.selectSkuList(sku);
        return getDataTable(list);
    }

//    /**
//     * 查询商品管理
//     *
//     * @param skuId 商品管理主键
//     * @return 商品管理
//     */
//    @PreAuthorize("@ss.hasPermi('manage:sku:query')")
//    @GetMapping(value = "/{skuId}")
//    public Sku selectSkuBySkuId(@PathVariable("skuId") Long skuId)
//    {
//        return skuService.selectSkuBySkuId(skuId);
//    }

    /**
     * 导出商品管理列表
     */
    @PreAuthorize("@ss.hasPermi('manage:sku:export')")
    @Log(title = "商品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Sku sku)
    {
        List<Sku> list = skuService.selectSkuList(sku);
        ExcelUtil<Sku> util = new ExcelUtil<Sku>(Sku.class);
        util.exportEasyExcel(response, list, "商品管理数据");
    }

//    导入商品列表
    @PreAuthorize("@ss.hasPermi('manage:sku:add')")
    @Log(title = "商品管理", businessType = BusinessType.IMPORT)
    @PostMapping("/import")
    public AjaxResult excelImport(MultipartFile file) throws Exception
    {
        ExcelUtil<Sku> util = new ExcelUtil<>(Sku.class);
        List<Sku> list = util.importEasyExcel(file.getInputStream());
        list.forEach(sku -> sku.setCreateTime(new Date()));
        list.forEach(sku -> sku.setUpdateTime(new Date()));
        log.info("导入商品列表：{}", list);
        return success(skuService.insertSkuList(list));
    }

    /**
     * 获取商品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:sku:query')")
    @GetMapping(value = "/{skuId}")
    public AjaxResult getInfo(@PathVariable("skuId") Long skuId)
    {
        return success(skuService.selectSkuBySkuId(skuId));
    }

    /**
     * 新增商品管理
     */
    @PreAuthorize("@ss.hasPermi('manage:sku:add')")
    @Log(title = "商品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Sku sku)
    {
        return toAjax(skuService.insertSku(sku));
    }

    /**
     * 修改商品管理
     */
    @PreAuthorize("@ss.hasPermi('manage:sku:edit')")
    @Log(title = "商品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Sku sku)
    {
        return toAjax(skuService.updateSku(sku));
    }

    /**
     * 删除商品管理
     */
    @PreAuthorize("@ss.hasPermi('manage:sku:remove')")
    @Log(title = "商品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{skuIds}")
    public AjaxResult remove(@PathVariable Long[] skuIds)
    {
        return toAjax(skuService.deleteSkuBySkuIds(skuIds));
    }
}
