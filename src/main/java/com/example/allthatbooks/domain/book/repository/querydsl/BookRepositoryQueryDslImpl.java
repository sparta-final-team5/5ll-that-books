package com.example.allthatbooks.domain.book.repository.querydsl;

import com.example.allthatbooks.domain.book.dto.response.BookListResponse;
import com.example.allthatbooks.domain.book.dto.response.BookTagResponse;
import com.example.allthatbooks.domain.book.entity.Book;
import com.example.allthatbooks.domain.book.entity.BookTag;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.allthatbooks.domain.book.entity.QBook.book;
import static com.example.allthatbooks.domain.book.entity.QBookTag.bookTag;

@Repository
@RequiredArgsConstructor
public class BookRepositoryQueryDslImpl implements BookRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BookListResponse> findAllPaging(Pageable pageable, String search) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(book.deletedAt.isNull());

        if (search != null && !search.isBlank()) {
            builder.and(
                book.title.containsIgnoreCase(search)
                    .or(book.author.containsIgnoreCase(search))
                    .or(book.translator.containsIgnoreCase(search))
            );
        }

        List<Tuple> tuples = queryFactory
            .select(book, bookTag)
            .from(book)
            .leftJoin(book.bookTagSet, bookTag)
            .where(builder)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Map<Book, List<BookTag>> grouped = tuples.stream()
            .collect(Collectors.groupingBy(
                tuple -> tuple.get(book),
                Collectors.mapping(tuple -> tuple.get(bookTag), Collectors.toList())
            ));

        List<BookListResponse> results = grouped.entrySet().stream()
            .map(entry -> BookListResponse.toDto(
                entry.getKey(),
                entry.getValue().stream()
                    .map(BookTagResponse::toDto)
                    .toList()
            ))
            .toList();

        Long total = queryFactory
            .select(book.count())
            .from(book)
            .leftJoin(book.bookTagSet, bookTag)
            .where(builder)
            .fetchOne();

        return new PageImpl<>(results, pageable, total != null ? total : 0);
    }

}
