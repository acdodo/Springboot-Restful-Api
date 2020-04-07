package com.demo.app.common;

import oracle.sql.CLOB;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClobTypeHandler implements TypeHandler<Object> {

    public Object valueOf(String param){
        return null;
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        CLOB clob = CLOB.empty_lob();
        clob.setString(1,(String) o);
        preparedStatement.setClob(i,clob);
    }

    @Override
    public Object getResult(ResultSet resultSet, String s) throws SQLException {
        CLOB clob = (CLOB)resultSet.getClob(s);
        return (clob == null || clob.length() == 0) ? null : clob.getSubString((long)1,(int)clob.length());
    }

    @Override
    public Object getResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Object getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
