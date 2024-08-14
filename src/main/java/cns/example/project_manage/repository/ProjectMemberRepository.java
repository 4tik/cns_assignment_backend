package cns.example.project_manage.repository;

import cns.example.project_manage.model.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    public List<ProjectMember> getByUserId(Long userId);
    public List<ProjectMember> getByProjectId(Long projectId);
    @Modifying
    public void deleteByProjectId(Long projectId);
}
