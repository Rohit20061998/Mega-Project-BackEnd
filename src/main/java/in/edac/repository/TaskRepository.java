package in.edac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.edac.model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{

}