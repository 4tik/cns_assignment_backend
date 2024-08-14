package cns.example.project_manage.service.impl;

import cns.example.project_manage.bindings.ProjectMemberAssignPayload;
import cns.example.project_manage.model.ProjectMember;
import cns.example.project_manage.repository.ProjectMemberRepository;
import cns.example.project_manage.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;

    @Override
    public void store(ProjectMemberAssignPayload projectMemberAssignPayload) {
        ProjectMember projectMember = ProjectMember.builder()
                .userId(projectMemberAssignPayload.getUserId())
                .projectId(projectMemberAssignPayload.getProjectId())
                .createdOn(new Date(System.currentTimeMillis()))
                .createdBy(projectMemberAssignPayload.getCurrentUserId())
                .build();
        projectMemberRepository.save(projectMember);
    }

    @Override
    public List<ProjectMember> getByUserId(Long userId) {
        return projectMemberRepository.getByUserId(userId);
    }

    @Override
    public List<ProjectMember> getByProjectId(Long projectId) {
        return projectMemberRepository.getByProjectId(projectId);
    }

    @Override
    public void deleteByProjectId(Long projectId) {
        try{
            projectMemberRepository.getByProjectId(projectId)
                    .stream().forEach(projectMember -> {
                     projectMemberRepository.deleteById(projectMember.getId());
            });
        }
        catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
}
