package com.example.hibernate.repository;

import com.example.hibernate.entity.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class Repository implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var names = List.of("Nina", "Mike", "Alex", "Kate", "Kiko");
        var cities = List.of("Yakutsk", "Moscow", "NewYork", "Tokyo", "Vienna");
        var surnames = List.of("Ever", "Scott", "Lotner", "Spring", "Chang");
        var random = new Random();
        IntStream.range(0, 50)
                .forEach(i -> {
                    var person = Person.builder()
                            .name(names.get(random.nextInt(names.size())))
                            .surname(surnames.get(random.nextInt(surnames.size())))
                            .age(random.nextInt(60))
                            .number(String.valueOf(8999 + i))
                            .city(cities.get(random.nextInt(cities.size())))
                            .build();
                    entityManager.persist(person);
                });
    }

    public List findByCity(String city) {
        List persons;
        Query query = entityManager.createQuery("select p from Person p where p.city=:city");
        query.setParameter("city", city);
        persons = query.getResultList();
        return persons;
    }
}