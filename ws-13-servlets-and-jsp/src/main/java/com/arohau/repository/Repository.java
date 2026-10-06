package com.arohau.repository;

import com.arohau.domain.Page;

public interface Repository<ID, CLASS> {
    Long getNextId();

    CLASS findBy(ID id);
    Page<CLASS> getUsers(Page<CLASS> page);

    ID save(CLASS user);
    CLASS update(CLASS user);
    void delete(ID id);
}
