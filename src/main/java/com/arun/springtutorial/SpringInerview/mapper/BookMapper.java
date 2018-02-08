package com.arun.springtutorial.SpringInerview.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.arun.springtutorial.SpringInerview.bean.Books;

public class BookMapper implements RowMapper<Books> {

	@Override
	public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
		Books book = new Books();
		book.setId(rs.getLong(1));
		book.setAuthorName(rs.getString(2));
		book.setBookName(rs.getString(3));
		book.setPublishedDate(rs.getDate(4));
		book.setPublisherName(rs.getString(5));
		return book;
	}

}
