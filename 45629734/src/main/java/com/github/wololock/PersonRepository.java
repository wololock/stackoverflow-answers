package com.github.wololock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE ((p.firstName = :firstName AND p.lastName = :lastName) OR (p.firstName = :lastName AND p.lastName = :firstName)) AND p.age = :age")
    Person findWithCustomQuery(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("age") Integer age);
}
