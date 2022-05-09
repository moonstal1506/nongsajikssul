package com.nongsa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "likes_uk", columnNames = { "boardId","userId" }) })
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name="boardId")
	@ManyToOne
	private Board board;
	
	@JsonIgnoreProperties({"boards"})
	@JoinColumn(name="userId")
	@ManyToOne
	private User user;

	@CreationTimestamp
	private Timestamp createDate;
}
