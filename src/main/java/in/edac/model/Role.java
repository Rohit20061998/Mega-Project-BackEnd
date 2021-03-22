package in.edac.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
	@Table(name = "roles")
	public class Role {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Enumerated(EnumType.STRING)
	    @NaturalId
	    @Column(length = 60)
	    private RoleName name;
	    
	    @OneToOne(mappedBy = "role")
	    @JsonIgnore
	    private User user;
	    
	    public Role() {}

	    public Role(RoleName name) {
	        this.name = name;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public RoleName getName() {
	        return name;
	    }

	    public void setName(RoleName name) {
	        this.name = name;
	    }

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	}