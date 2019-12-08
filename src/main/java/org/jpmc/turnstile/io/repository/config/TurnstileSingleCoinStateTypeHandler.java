package org.jpmc.turnstile.io.repository.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.jpmc.turnstile.core.service.SingleCoinTurnstileState;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnstileSingleCoinStateTypeHandler extends BaseTypeHandler<SingleCoinTurnstileState> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, SingleCoinTurnstileState singleCoinTurnstileState, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(columnIndex, singleCoinTurnstileState.name());
    }

    @Override
    public SingleCoinTurnstileState getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return SingleCoinTurnstileState.valueOf(resultSet.getString(columnName));
    }

    @Override
    public SingleCoinTurnstileState getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return SingleCoinTurnstileState.valueOf(resultSet.getString(columnIndex));
    }

    @Override
    public SingleCoinTurnstileState getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return SingleCoinTurnstileState.valueOf(callableStatement.getString(columnIndex));
    }
}
