package com.arun.springtutorial.SpringInerview.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arun.springtutorial.SpringInerview.bean.Books;
import com.arun.springtutorial.SpringInerview.repository.BookInterfaceRepository;

@Service
public class BookService implements BookInterfaceService{

	@Autowired
	private BookInterfaceRepository bookInterfaceRepository;
	
	@Override
	@Transactional
	public List<Books> getAllBooks() {
		return bookInterfaceRepository.getAllBooks();
	}

	@Override
	@Transactional
	public long createABook(Books book) {
		return bookInterfaceRepository.createABook(book);
	}

	@Override
	@Transactional
	public Books getABook(long id) {
		return bookInterfaceRepository.getABook(id);
	}

	@Override
	@Transactional
	public Boolean deleteABook(long id) {
		return bookInterfaceRepository.deleteABook(id);
	}

	@Override
	public List<Books> getBooksWithCourses() {
		// TODO Auto-generated method stub
		return bookInterfaceRepository.getBooksWithCourses();
	}

}
