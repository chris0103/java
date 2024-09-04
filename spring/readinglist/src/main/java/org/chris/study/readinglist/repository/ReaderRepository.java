package org.chris.study.readinglist.repository;

import org.chris.study.readinglist.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, String> {

}
