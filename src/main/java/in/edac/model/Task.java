package in.edac.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import in.edac.repository.CustomerDateAndTimeDeserialize;

@Entity

public class Task{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="task_id")
	private long taskId;
	private String taskName;
	@ManyToOne
	 @JoinColumn(name="emp_id")
    private Employee employee1;

//	private long emp_id;
	@ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	private Date startDate;

@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	private Date endDate;
	private String status;
//	private String assignedBy;
	   
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="assignedBy",referencedColumnName = "id")
    private User teamLead;
	public Task() {
		super();
	}

	public Task(String taskName, Employee employee1, Project project, Date startDate, Date endDate, String status,
			User teamLead) {
		super();
		this.taskName = taskName;
		this.employee1 = employee1;
		this.project = project;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.teamLead = teamLead;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Employee getEmployee1() {
		return employee1;
	}

	public void setEmployee1(Employee employee1) {
		this.employee1 = employee1;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getTeamLead() {
		return teamLead;
	}

	public void setTeamLead(User teamLead) {
		this.teamLead = teamLead;
	}

	
	
	
}