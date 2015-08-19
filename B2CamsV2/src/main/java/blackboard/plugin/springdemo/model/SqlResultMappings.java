package blackboard.plugin.springdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;

@Entity
@SqlResultSetMapping(name="CourseEnrollmentMapping",
classes = {
		@ConstructorResult(targetClass = EnrUserToCourse.class,
			columns = {
			@ColumnResult(name="username"), 
			@ColumnResult(name="role")}//TODO: add actual object fields
		)}
)

public class SqlResultMappings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column
	private String username;
	

	

}
