package sba.group3.clinic.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sba.group3.clinic.auth.entity.OtpChallenge;

import java.util.UUID;

public interface OtpChallengeRepository extends JpaRepository<OtpChallenge, UUID>, JpaSpecificationExecutor<OtpChallenge> {
}