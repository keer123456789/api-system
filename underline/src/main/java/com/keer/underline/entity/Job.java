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
 * job信息表
 * </p>
 *
 * @author young
 * @since 2023年08月19日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("job")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务id
     */
    @TableField("task_id")
    private Integer taskId;

    /**
     * job开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * job 结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 线程id
     */
    @TableField("thread_id")
    private String threadId;

    /**
     * 任务参数，定义为json字符串
     */
    @TableField("param")
    private String param;

    /**
     * 测试结果地址
     */
    @TableField("result_path")
    private String resultPath;

    /**
     * 任务运行次数
     */
    @TableField("run_num")
    private Integer runNum;

    /**
     * 下载次数
     */
    @TableField("download_num")
    private Integer downloadNum;

    /**
     * 任务状态 0-未开始；1-运行中；2-任务结束；3-任务异常
     */
    @TableField("status")
    private Integer status;

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


}
