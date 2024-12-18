package org.chris.study.readinglist.repository;

import org.chris.study.readinglist.entity.Book;
import org.chris.study.readinglist.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(Reader reader);
}
