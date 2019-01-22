package org.sample.shop.common.db.html;

public class Metadata {

    private String sql;

    private String type;

    public Metadata(String sql) {
        this.sql = sql;
    }

    public Metadata(String sql, String type) {
        this.sql = sql;
        this.type = type;
    }

    public String getSql() {
        return sql;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "sql='" + sql + '\'' +
                '}';
    }
}
