package sba.group3.clinic.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sba.group3.clinic.auth.entity.LoginAttempt;

import java.util.UUID;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, UUID>, JpaSpecificationExecutor<LoginAttempt> {
}