package com.keer.underline.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * api信息表
 * </p>
 *
 * @author young
 * @since 2023年08月19日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("api")
public class Api implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * api名称
     */
    @TableField("name")
    private String name;

    /**
     * API 地址
     */
    @TableField("url")
    private String url;

    /**
     * 请求方式 1-GET 2-POST
     */
    @TableField("method")
    private Integer method;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("jar_path")
    private String jarPath;


}
