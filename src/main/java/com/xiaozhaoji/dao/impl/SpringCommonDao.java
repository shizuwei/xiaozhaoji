/**
 * Baijiahulian.com Inc. Copyright (c) 2014-2015 All Rights Reserved.
 */

package com.xiaozhaoji.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public abstract class SpringCommonDao {
    // private static final Logger log = LoggerFactory.getLogger(SpringCommonDao.class);
    @Autowired(required = false)
    @Qualifier("dataSource")
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public DataSource getDataSource() {

        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    public JdbcTemplate getJdbcTemplate() {

        return jdbcTemplate;
    }

    @Autowired(required = true)
    public void setJdbcTemplate(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null) {
            throw new IllegalArgumentException("jdbcTemplate == null");
        }
        this.jdbcTemplate = jdbcTemplate;
        this.setNamedJdbcTemplate(new NamedParameterJdbcTemplate(this.jdbcTemplate));
    }

    /**
     * @return the namedJdbcTemplate
     */

    public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
        if (this.namedJdbcTemplate == null) {
            if (this.jdbcTemplate != null) {
                this.namedJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
            } else {
                throw new IllegalArgumentException("jdbcTemplate == null");
            }
        }

        return this.namedJdbcTemplate;

    }

    public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {

        this.namedJdbcTemplate = namedJdbcTemplate;

    }

}
