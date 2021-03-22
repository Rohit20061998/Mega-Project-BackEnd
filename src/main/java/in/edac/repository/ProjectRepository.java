package in.edac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.edac.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
	//public Project addProject(Project project);
}
