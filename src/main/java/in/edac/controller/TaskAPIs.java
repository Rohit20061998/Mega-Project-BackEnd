package in.edac.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.edac.exception.ResourceNotFoundException;
import in.edac.message.request.TaskForm;
import in.edac.model.Employee;
import in.edac.model.Project;
import in.edac.model.Task;
import in.edac.model.User;
import in.edac.repository.EmployeeRepository;
import in.edac.repository.ProjectRepository;
import in.edac.repository.TaskRepository;
import in.edac.repository.UserRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class TaskAPIs {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/task")
	public List<Task> getAllTask(){
		return taskRepository.findAll();
	}	
	@PostMapping("/task")
	public Task createTask(@RequestBody TaskForm task1, HttpServletRequest request) {
		Task task= new Task();
		Employee employee=  employeeRepository.findById(task1.getEmpid()).orElseThrow(()-> new RuntimeException("Fail!-> Cause:Employee not found."));
		 task.setEmployee1(employee);
		
		Project project = projectRepository.findById(task1.getProjectid()).orElseThrow(()-> new RuntimeException("Fail!-> Cause:Project not found."));
		task.setProject(project);
		
		task.setStartDate(task1.getStartdate());
		task.setEndDate(task1.getEnddate());
		task.setTaskName(task1.getTaskname());
		task.setStatus(task1.getStatus());
		
		Principal principal = request.getUserPrincipal();
		
		
		
		String name = principal.getName();
		if(name != null) {
			User teamLead = userRepository.findByUsername(name)
			.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Project Lead not find."));
			task.setTeamLead(teamLead);
		}

		return taskRepository.save(task);
	}
	
	@DeleteMapping("/task/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteTask(@PathVariable Long id){
		Task task=taskRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Task not exist with id"+ id));
		
	taskRepository.delete(task);
	Map<String,Boolean> response = new HashMap<>();
	response.put("deleted",Boolean.TRUE);
	return ResponseEntity.ok(response);
	}
	@PutMapping("/task/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails){
		
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task Does Not Exist"));
		
		
		task.setTaskName(taskDetails.getTaskName());
		task.setEmployee1(taskDetails.getEmployee1());
		task.setProject(taskDetails.getProject());
		task.setStartDate(taskDetails.getStartDate());
		task.setEndDate(taskDetails.getEndDate());
		task.setStatus(taskDetails.getStatus());
		task.setTeamLead(taskDetails.getTeamLead());
		Task updatedTask =taskRepository.save(task);
		return ResponseEntity.ok(updatedTask);
}
}
