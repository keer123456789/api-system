CREATE TABLE `task`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`           varchar(30)  DEFAULT NULL COMMENT '任务名称',
    `customer`       varchar(30)  DEFAULT NULL COMMENT '测试客户名称',
    `sale_man`       varchar(30)  DEFAULT NULL COMMENT '我方商务人员',
    `api_id`         varchar(30)  DEFAULT NULL COMMENT 'API id',
    `param`          text         DEFAULT NULL COMMENT '任务参数，定义为json字符串',
    `result_path`    varchar(255) DEFAULT NULL COMMENT '测试结果地址',
    `run_num`        int(11) DEFAULT NULL COMMENT '任务运行次数',
    `download_num`   int(11) DEFAULT NULL COMMENT '下载次数',
    `status`         int(11) DEFAULT NULL COMMENT '任务状态 0-未开始；1-运行中；2-任务结束；3-任务异常',
    `test_file_path` varchar(255) DEFAULT NULL COMMENT '测试名单文件地址',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`    datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT '任务信息表';

CREATE TABLE `job`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `task_id`      int(11) DEFAULT NULL COMMENT '任务id',
    `start_time`   datetime     DEFAULT NULL COMMENT 'job开始时间',
    `end_time`     datetime     DEFAULT NULL COMMENT 'job 结束时间',
    `thread_id`    varchar(50)  DEFAULT NULL COMMENT '线程id',
    `param`        text         DEFAULT NULL COMMENT '任务参数，定义为json字符串',
    `result_path`  varchar(255) DEFAULT NULL COMMENT '测试结果地址',
    `run_num`      int(11) DEFAULT NULL COMMENT '任务运行次数',
    `download_num` int(11) DEFAULT NULL COMMENT '下载次数',
    `status`       int(11) DEFAULT NULL COMMENT '任务状态 0-未开始；1-运行中；2-任务结束；3-任务异常',
    `create_time`  datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT 'job信息表';

CREATE TABLE `api`
(
    `id`                int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`              varchar(255) DEFAULT NULL COMMENT 'api名称',
    `url`               text         DEFAULT NULL COMMENT 'API 地址',
    `method`            int(11) DEFAULT NULL COMMENT '请求方式 1-GET 2-POST',
    `transfer_jar_path` varchar(255) DEFAULT NULL COMMENT '转化jar包地址',
    `create_time`       datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT 'api信息表';