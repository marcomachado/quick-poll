package com.masm.quickpoll.repository;

import com.masm.quickpoll.model.Option;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
