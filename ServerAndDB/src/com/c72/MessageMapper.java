package com.c72;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
public class MessageMapper implements RowMapper<Message> {
   public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
      Message message=new Message();
      message.setTid(rs.getInt("tid"));
      message.setUid(rs.getInt("uid"));
      message.setTime(rs.getTimestamp("time"));
      message.setFloor(rs.getInt("floor"));
      message.setDetail(rs.getString("detail"));
      return message;
   }
}