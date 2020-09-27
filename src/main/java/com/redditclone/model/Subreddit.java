package com.redditclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Subreddit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long subredditId;
	
	@NotBlank(message = "Community name is required")
    private String name;
	
	@NotBlank(message = "Description is required")
    private String description;
	
	private Instant createdDate;
	
	@OneToMany(fetch = FetchType.LAZY)
    private List<Post> post;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private User user;
}