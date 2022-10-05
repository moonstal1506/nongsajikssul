package com.nongsa.sns.repository;

import com.nongsa.sns.dto.BoardSearchDto;
import com.nongsa.sns.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface  BoardRepositoryCustom {

    Page<Board> getBoardListPage(BoardSearchDto BoardSearchDto, Pageable pageable);
}
