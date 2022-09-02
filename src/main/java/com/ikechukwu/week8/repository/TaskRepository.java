package com.ikechukwu.week8.repository;

import com.ikechukwu.week8.enums.Status;
import com.ikechukwu.week8.model.Task;
import com.ikechukwu.week8.model.User;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmParentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> getTaskByStatus(Status status);

    List<Task> getTasksByUser(User user);

    @Modifying
    @Query(value = "UPDATE tasks SET status = ?1 WHERE id = ?2", nativeQuery = true)
    boolean updateTaskByIdAndStatus(String status, long id);
}
