package com.arun.springtutorial.SpringInerview.repository;

import java.util.List;

import com.arun.springtutorial.SpringInerview.bean.Books;

public interface BookInterfaceRepository {

	List<Books> getAllBooks();

	long createABook(Books book);

	Books getABook(long id);

	Boolean deleteABook(long id);

}
