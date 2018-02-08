package com.arun.springtutorial.SpringInerview.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arun.springtutorial.SpringInerview.bean.Books;
import com.arun.springtutorial.SpringInerview.repository.BookInterfaceRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Book", description = "Details of Books")
public class BooksController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookInterfaceRepository bookInterfaceRepository;

	/**
	 * 
	 * @return
	 * @GetMapping is shortcut to @RequestMapping(path="/v1/public/books",
	 *             method=RequestMethod.GET)
	 * 
	 *             ResponseEntity sends the response with body and status but
	 *             with no header.
	 */
	@ApiOperation(value = "view all the books", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Message Retrieved Successfully"),
			@ApiResponse(code = 401, message = "Access denied") })
	@GetMapping(path = "/v1/public/books", produces = "application/json", name = "getAllBooks")
	public ResponseEntity<List<Books>> getAllBooks() {
		List<Books> books = bookInterfaceRepository.getAllBooks();
		return new ResponseEntity<List<Books>>(books, HttpStatus.OK);
	}

	@GetMapping("/v1/public/books/{id}")
	public ResponseEntity<Books> getABook(@PathVariable final long id) {
		Books book = bookInterfaceRepository.getABook(id);
		return new ResponseEntity<Books>(book, HttpStatus.OK);
	}

	@PutMapping("/v1/public/books/create")
	public ResponseEntity<Long> createABook(@RequestBody Books book) {
		log.info("Inside create book {}", book);
		long primaryKey = bookInterfaceRepository.createABook(book);
		return new ResponseEntity<Long>(primaryKey, HttpStatus.CREATED);
	}

	@DeleteMapping("/v1/public/books/delete/{id}")
	public ResponseEntity<Boolean> deleteABook(@PathVariable final long id) {
		boolean isDeleted = bookInterfaceRepository.deleteABook(id);
		return new ResponseEntity<Boolean>(isDeleted, HttpStatus.GONE);
	}
}
