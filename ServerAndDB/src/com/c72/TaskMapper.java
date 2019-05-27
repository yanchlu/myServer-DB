package com.c72;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
public class TaskMapper implements RowMapper<Task> {
   public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
      Task task = new Task();
      task.setTid(rs.getInt("tid"));
      task.setUid(rs.getInt("uid"));
      task.setTitle(rs.getString("title"));
      task.setDetail(rs.getString("detail"));
      task.setMoney(rs.getInt("money"));
      task.setType(rs.getString("type"));
      task.setTotal_num(rs.getInt("total_num"));
      task.setCurrent_num(rs.getInt("current_num"));
      task.setStart_time(rs.getTimestamp("start_time"));
      task.setEnd_time(rs.getTimestamp("end_time"));
      task.setState(rs.getString("state"));
      return task;
   }
}