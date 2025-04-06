package com.giov.todosimple.repositories;

import com.giov.todosimple.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //METODO HERDADO QUE PROCURA O ID
    List<Task> findByUser_Id(Long id);

//    @Query(value = " SELECT t " +
//                   " FROM task t" +
//                   " WHERE t.user.id = :id ") //ANNOTATION QUE PERMITE REALIZAR QUERYS COMO SE FOSSE EM UM SCRIPT SQL
//    List<Task> findByUser_Id(@Param("id") Long id);

    //CRIACAO COM SQL PURO
//    @Query(value = "SELECT * FROM task t WHERE t.user.id = :id", nativeQuery = true )
//    List<Task> findByUser_Id(@Param("id") Long id);
}
