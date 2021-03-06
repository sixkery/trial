package com.sixkery.utils;

import com.sixkery.entity.TableRequest;
import com.zaxxer.hikari.HikariDataSource;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 数据库工具类
 * </p>

 */
@Slf4j
@UtilityClass
public class DbUtil {

    public HikariDataSource buildFromTableRequest(TableRequest request) {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(request.getPrepend() + request.getUrl());
        dataSource.setUsername(request.getUsername());
        dataSource.setPassword(request.getPassword());
        return dataSource;
    }

}
