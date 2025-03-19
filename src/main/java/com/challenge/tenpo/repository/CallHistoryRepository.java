package com.challenge.tenpo.repository;

import com.challenge.tenpo.model.Call;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallHistoryRepository extends JpaRepository<Call, Long> {

    Page<Call> findAllByOrderByFechaDesc(Pageable pageable);
}
