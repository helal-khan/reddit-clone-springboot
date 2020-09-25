package com.redditclone.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redditclone.model.Post;
import com.redditclone.model.Subreddit;
import com.redditclone.model.User;

public interface PostRepository extends JpaRepository<Post, Long> {
	
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}