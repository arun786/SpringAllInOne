package com.arun.springtutorial.SpringInerview.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.arun.springtutorial.SpringInerview.bean.Books;
import com.arun.springtutorial.SpringInerview.mapper.BookMapper;
import com.mysql.jdbc.Statement;

@Repository
public class BookRepository implements BookInterfaceRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Books> getAllBooks() {
		List<Books> books = jdbcTemplate.query("select * from books", new BookMapper());
		return books;
	}

	@Override
	public Books getABook(long id) {

		Books book = jdbcTemplate.queryForObject("select * from books where id = ?", new Object[] { id },
				new BookMapper());
		return book;
	}

	@Override
	public Boolean deleteABook(long id) {
		Books book = getABook(id);
		int rowsAffected = 0;
		if (null != book) {
			rowsAffected = jdbcTemplate.update("delete from books where id = ?", new Object[] { id });
		}
		return rowsAffected > 0 ? true : false;
	}

	@Override
	public long createABook(Books book) {

		String sql = "insert into books (author_name,book_name,published_date,publisher_name)";
		KeyHolder key = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				final PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, book.getAuthorName());
				ps.setString(2, book.getBookName());
				ps.setDate(3, (Date) book.getPublishedDate());
				ps.setString(4, book.getPublisherName());
				return ps;
			}
		}, key);
		return key.getKey().longValue();
	}
}
