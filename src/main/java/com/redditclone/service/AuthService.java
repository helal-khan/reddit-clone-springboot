package com.redditclone.service;

import java.time.Instant;
import java.util.UUID;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.redditclone.exception.RedditCloneException;
import com.redditclone.model.NotificationEmail;
import com.redditclone.model.User;
import com.redditclone.model.VerificationToken;
import com.redditclone.repository.UserRepository;
import com.redditclone.repository.VerificationTokenRepository;
import com.redditclone.util.Constants;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void signup(User user) {
		user.setPassword(encodePassword(user.getPassword()));
		user.setCreatedDate(Instant.now());
		user.setEnabled(false);

		userRepository.save(user);

		String token = generateVerificationToken(user);

		mailService.sendMail(new NotificationEmail("Please Activate your Account", user.getEmail(),
				"Thank you for signing up to Spring Reddit, please click on the below url to activate your account : "
						+ Constants.ACTIVATION_EMAIL + token));
	}

	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		verificationToken.orElseThrow(() -> new RedditCloneException("Invalid Token"));
		fetchUserAndEnable(verificationToken.get());
	}

	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		String username = verificationToken.getUser().getUsername();
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RedditCloneException("User not found with name - " + username));
		user.setEnabled(true);
		userRepository.save(user);
	}

	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

}
