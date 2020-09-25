package com.redditclone.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.redditclone.model.Post;
import com.redditclone.model.User;
import com.redditclone.model.Vote;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
