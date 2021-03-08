package com.ssafy.backend.service;

import com.ssafy.backend.dto.BookDto;
import com.ssafy.backend.dto.ReviewDto;

import java.util.List;

public interface BookService {
    int insertBook(BookDto book) throws Exception;
    int updateBook(BookDto book) throws Exception;
    int deleteBook(long book_isbn) throws Exception;

    BookDto selectBook(long book_isbn) throws Exception;
    List<ReviewDto> selectReviewList(long book_isbn) throws Exception;

}
