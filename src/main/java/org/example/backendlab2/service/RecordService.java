package org.example.backendlab2.service;

import org.example.backendlab2.model.Account;
import org.example.backendlab2.model.Category;
import org.example.backendlab2.model.Person;
import org.example.backendlab2.model.Record;
import org.example.backendlab2.repository.AccountRepository;
import org.example.backendlab2.repository.CategoryRepository;
import org.example.backendlab2.repository.PersonRepository;
import org.example.backendlab2.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    private final RecordRepository recordRepository;
    private final PersonRepository personRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository, PersonRepository personRepository,
                         CategoryRepository categoryRepository, AccountRepository accountRepository) {
        this.recordRepository = recordRepository;
        this.personRepository = personRepository;
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
    }

    // Створення нового запису
    public Record createRecord(Long userId, Long categoryId, BigDecimal amount) {
        // Перевірка наявності користувача
        Person person = personRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Перевірка наявності категорії
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        // Перевірка балансу рахунку
        Account account = person.getAccount();
        if (account == null) {
            throw new IllegalArgumentException("Account for user does not exist");
        }

        // Перевірка, чи достатньо коштів на рахунку
        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        // Списання коштів з рахунку
        account.deductFunds(amount, false); // false забороняє вхід в мінус

        // Створення нового запису
        Record record = new Record();
        record.setPerson(person);
        record.setCategory(category);
        record.setAmount(amount);
        record.setDateTime(java.time.LocalDateTime.now());

        return recordRepository.save(record);
    }

    // Отримати запис по ID
    public Optional<Record> getRecord(Long recordId) {
        return recordRepository.findById(recordId);
    }

    // Отримати всі записи
    public List<Record> getAllRecords() {
        return (List<Record>) recordRepository.findAll();
    }

    // Видалити запис по ID
    public void deleteRecord(Long recordId) {
        recordRepository.deleteById(recordId);
    }

    // Додаткові методи для пошуку записів
    public List<Record> getRecordsByPersonId(Long personId) {
        return recordRepository.findByPerson_Id(personId);
    }

    public List<Record> getRecordsByCategoryId(Long categoryId) {
        return recordRepository.findByCategory_Id(categoryId);
    }

    public List<Record> getRecordsByPersonName(String personName) {
        return recordRepository.findByPerson_Name(personName);
    }

    public List<Record> getRecordsByCategoryName(String categoryName) {
        return recordRepository.findByCategory_Name(categoryName);
    }
}
