package sba.group3.backendmvc.repository.organization;

import sba.group3.backendmvc.entity.organization.Clinic;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.UUID;

public interface ClinicRepository extends BaseRepository<Clinic, UUID> {
    Clinic findByName(String name);
}