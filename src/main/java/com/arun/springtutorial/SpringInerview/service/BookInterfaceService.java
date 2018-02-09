package com.arun.springtutorial.SpringInerview.service;

import java.util.List;

import com.arun.springtutorial.SpringInerview.bean.Books;

public interface BookInterfaceService {
	List<Books> getAllBooks();

	long createABook(Books book);

	Books getABook(long id);

	Boolean deleteABook(long id);
	
	public List<Books> getBooksWithCourses();

}
