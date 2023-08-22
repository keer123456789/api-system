package com.keer.underline.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.keer.common.entity.Result;
import com.keer.underline.entity.Api;
import com.keer.underline.service.ApiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * api信息表 前端控制器
 * </p>
 *
 * @author keer
 * @since 2023年08月19日
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Resource
    private ApiService apiService;
    @Value("${dir.jar}")
    private String jar_path;

    @PostMapping("/add")
    public Result add(@RequestBody Api api) {
        if (api == null ||
                StringUtils.isEmpty(api.getName()) ||
                StringUtils.isEmpty(api.getUrl()) ||
                api.getMethod() == null) {
            return Result.error("参数缺失");
        }
        api.setCreateTime(LocalDateTime.now());
        api.setUpdateTime(LocalDateTime.now());
        QueryWrapper<Api> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", api.getName());
        Api old = apiService.getOne(queryWrapper);
        if (old != null) {
            return Result.error("名称重复");
        }
        if (apiService.save(api)) {
            return Result.ok();
        } else {
            return Result.error("数据库错误");
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Api api) {
        if (api == null ||
                api.getId()!=null||
                StringUtils.isEmpty(api.getName()) ||
                StringUtils.isEmpty(api.getUrl()) ||
                api.getMethod() == null) {
            return Result.error("参数缺失");
        }

        api.setUpdateTime(LocalDateTime.now());

        if (apiService.updateById(api)) {
            return Result.ok();
        } else {
            return Result.error("数据库错误");
        }
    }

    @GetMapping("/page")
    public Result page(@RequestParam(required = false) String name,
                       @RequestParam(required = false) String url,
                       @RequestParam(required = false) Integer method,
                       @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                       @RequestParam(required = false, defaultValue = "1") Integer pageNum) {

        IPage<Api> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Api> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            wrapper.like("name", name);
        }
        if (StringUtils.isNotEmpty(url)) {
            wrapper.like("url", url);
        }
        if (method != null) {
            wrapper.eq("method", method);
        }
        wrapper.orderByDesc("create_time");
        page = apiService.page(page, wrapper);
        return Result.ok(page);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        if (apiService.removeById(id)) {
            return Result.ok();
        } else {
            return Result.error("删除失败！！");
        }
    }

    @PostMapping("/upload-jar/{id}")
    public Result addTransferJar(@RequestParam("file") MultipartFile file, @PathVariable String id) {
        if (!file.getOriginalFilename().endsWith(".jar")) {
            return Result.error("目前只支持jar包");
        }
        Api api = apiService.getById(id);
        if (api == null) {
            return Result.error("API 不存在");
        }
        String path = jar_path + api.getId();
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        path = path + "/" + file.getOriginalFilename();
        if (api.getJarPath() != null) {
            File oldJar = new File(api.getJarPath());
            if (oldJar.exists()) {
                String oldJarName = oldJar.getName();
                String newName =oldJar.getPath().replace(oldJarName,oldJarName + "." + System.currentTimeMillis() + ".bak");
                File newFile = new File(newName);
                oldJar.renameTo(newFile);
            }
        }
        api.setJarPath(path);
        api.setUpdateTime(LocalDateTime.now());
        //保存
        try {
            file.transferTo(new File(path));
            apiService.updateById(api);
            return Result.ok();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error("文件保存失败");
    }
}

