package com.wisely.ch8_2.support;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
// 此例中的接口继承了 JpaRepository，让我们具备了JpaRepository 所提供的方法；继承了 JpaSpecificationExecutor，让我们具备使用 Specification的能力。
public interface CustomRepository<T, ID extends Serializable>extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    Page<T> findByAuto(T example, Pageable pageable);
}
