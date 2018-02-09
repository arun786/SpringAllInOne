package com.arun.springtutorial.SpringInerview.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.arun.springtutorial.SpringInerview.bean.Books;
import com.arun.springtutorial.SpringInerview.bean.Course;
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

		String sql = "insert into books (author_name,book_name,published_date,publisher_name) values(?,?,?,?)";
		KeyHolder key = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				final PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, book.getAuthorName());
				ps.setString(2, book.getBookName());
				ps.setDate(3, new Date(book.getPublishedDate().getTime()));
				ps.setString(4, book.getPublisherName());
				return ps;
			}
		}, key);
		return key.getKey().longValue();
	}

	public List<Books> getBooksWithCourses() {
		String sql = "select * from books b, course c where b.id = c.id";
		@SuppressWarnings("unchecked")
		List<Books> lst = (List<Books>) jdbcTemplate.query(sql, new ResultSetExtractor<Object>() {

			List<Books> lstBooks = new ArrayList<>();

			@Override
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {
					Books book = new Books();
					Course course = new Course();
					book.setId(rs.getInt(1));
					book.setAuthorName(rs.getString(2));
					book.setBookName(rs.getString(3));
					book.setPublishedDate(rs.getDate(4));
					book.setPublisherName(rs.getString(5));
					course.setId(rs.getLong(6));
					course.setName(rs.getString(7));
					book.setCourse(course);
					lstBooks.add(book);
				}
				return lstBooks;
			}
		});
		return lst;
	}
}
