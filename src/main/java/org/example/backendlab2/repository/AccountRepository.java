package org.example.backendlab2.repository;

import org.example.backendlab2.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    // Знайти акаунт за ID користувача
    Optional<Account> findByPerson_Id(Long userId);

    // Знайти акаунт за користувачем
    Optional<Account> findByPerson_Name(String userName);

    // Якщо потрібно додати інші специфічні запити, можна додавати методи тут
}
