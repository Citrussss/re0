package com.king.re0.base.config;

import org.hibernate.spatial.dialect.mysql.MySQL56SpatialDialect;

public class MySQL5DialectUTF8 extends MySQL56SpatialDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
//        return super.getTableTypeString();
    }
}
