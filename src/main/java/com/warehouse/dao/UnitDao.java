package com.warehouse.dao;

import com.warehouse.beans.Unit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UnitDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Unit> getAllUnits() {
        String sql = "SELECT unit_id, unit_name FROM unit";
        return jdbcTemplate.query(sql, new UnitRowMapper());
    }

    public Unit getUnitById(int id) {
        String sql = "SELECT unit_id, unit_name FROM unit WHERE unit_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UnitRowMapper());
    }

    private static class UnitRowMapper implements RowMapper<Unit> {
        @Override
        public Unit mapRow(ResultSet rs, int rowNum) throws SQLException {
            Unit unit = new Unit();
            unit.setUnitId(rs.getInt("unit_id"));
            unit.setUnitName(rs.getString("unit_name"));
            return unit;
        }
    }
}
