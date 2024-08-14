package cns.example.project_manage.service;

import cns.example.project_manage.bindings.ProjectMemberAssignPayload;
import cns.example.project_manage.model.ProjectMember;

import java.util.List;

public interface ProjectMemberService {
    public void store(ProjectMemberAssignPayload projectMemberAssignPayload);
    public List<ProjectMember> getByUserId(Long userId);
    public List<ProjectMember> getByProjectId(Long projectId);
    public void deleteByProjectId(Long projectId);
}
