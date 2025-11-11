package sba.group3.backendmvc.repository.organization;

import sba.group3.backendmvc.entity.organization.Department;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.UUID;

public interface DepartmentRepository extends BaseRepository<Department, UUID> {
    Department findByName(String name);
}