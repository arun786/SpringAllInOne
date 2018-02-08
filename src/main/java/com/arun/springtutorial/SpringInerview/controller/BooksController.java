package com.arun.springtutorial.SpringInerview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arun.springtutorial.SpringInerview.bean.Books;
import com.arun.springtutorial.SpringInerview.repository.BookInterfaceRepository;

@RestController
public class BooksController {

	@Autowired
	private BookInterfaceRepository bookInterfaceRepository;

	@GetMapping("/v1/public/books")
	public ResponseEntity<List<Books>> getAllBooks() {
		List<Books> books = bookInterfaceRepository.getAllBooks();
		return new ResponseEntity<List<Books>>(books, HttpStatus.OK);
	}

	@GetMapping("/v1/public/books/{id}")
	public ResponseEntity<Books> getABook(@PathVariable final long id) {
		Books book = bookInterfaceRepository.getABook(id);
		return new ResponseEntity<Books>(book, HttpStatus.OK);
	}

	@PutMapping("/v1/private/book")
	public ResponseEntity<Long> createABook(@RequestBody Books book) {
		long primaryKey = bookInterfaceRepository.createABook(book);
		return new ResponseEntity<Long>(primaryKey, HttpStatus.CREATED);
	}
}
