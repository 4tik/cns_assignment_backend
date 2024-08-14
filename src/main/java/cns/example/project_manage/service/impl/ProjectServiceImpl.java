package cns.example.project_manage.service.impl;

import cns.example.project_manage.bindings.ProjectMemberAssignPayload;
import cns.example.project_manage.bindings.ProjectPayload;
import cns.example.project_manage.model.ProjectMember;
import cns.example.project_manage.model.Projects;
import cns.example.project_manage.projection.ProjectReportProjection;
import cns.example.project_manage.repository.ProjectsRepository;
import cns.example.project_manage.service.ProjectMemberService;
import cns.example.project_manage.service.ProjectService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor

public class ProjectServiceImpl implements ProjectService {
    private final ProjectsRepository projectsRepository;
    private final ProjectMemberService projectMemberService;

    @Override
    public List<Projects> getProjects() {

        return projectsRepository.findAll();
    }

    @Override
    public List<Projects> getProjectsByUserId(Long userId) {
        List<Projects> projects = new ArrayList<>();
        projectMemberService.getByUserId(userId)
                .stream().forEach(projectMember -> {
                    projects.add(getById(projectMember.getProjectId()));
        });
        return projects;
    }

    @Override
    public Projects getById(Long id) {
        Projects project =  projectsRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("project not found"));
        project.setProjectMembers(projectMemberService.getByProjectId(project.getId()));
        return project;
    }

    @Override
    public void store(ProjectPayload projectPayload) {
        Projects project = Projects.builder()
                .name(projectPayload.getName())
                .introduce(projectPayload.getIntroduce())
                .startDateTime(projectPayload.getStartDateTime())
                .endDateTime(projectPayload.getEndDateTime())
                .status(projectPayload.getStatus())
                .createdBy(projectPayload.getUserId())
                .createdOn(new Date(System.currentTimeMillis()))
                .build();
        projectsRepository.save(project);
        for (Long userId: projectPayload.getUsers()) {
            ProjectMemberAssignPayload projectMemberAssignPayload = ProjectMemberAssignPayload.builder()
                    .projectId(project.getId())
                    .userId(userId)
                    .currentUserId(projectPayload.getUserId())
                    .build();
            projectMemberService.store(projectMemberAssignPayload);
        }

    }

    @Override
    public void updateById(Long id, ProjectPayload projectPayload) {
        Projects project = getById(id);
        project.setName(projectPayload.getName());
        project.setIntroduce(projectPayload.getIntroduce());
        project.setStatus(projectPayload.getStatus());
        project.setStartDateTime(projectPayload.getStartDateTime());
        project.setEndDateTime(projectPayload.getEndDateTime());
        project.setUpdatedBy(projectPayload.getUserId());
        project.setUpdatedOn(new Date(System.currentTimeMillis()));
        projectsRepository.save(project);

        projectMemberService.deleteByProjectId(id);
        System.out.println(projectPayload);

        for (Long userId: projectPayload.getUsers()) {
            ProjectMemberAssignPayload projectMemberAssignPayload = ProjectMemberAssignPayload.builder()
                    .projectId(project.getId())
                    .userId(userId)
                    .currentUserId(projectPayload.getUserId())
                    .build();
            projectMemberService.store(projectMemberAssignPayload);
        }
    }

    @Override
    public void deleteById(Long id) {
        Projects project = getById(id);
        projectsRepository.deleteById(project.getId());
    }

    @Override
    public String getProjectsReport() throws IOException, JRException {

        String path = "E:\\spring-project\\reports";
        List<ProjectReportProjection> projectReportProjections = projectsRepository.getReports();

        File file = ResourceUtils.getFile("classpath:reports/projects_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(projectReportProjections);

        String reportFileName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        path = path+"\\report["+reportFileName+"].pdf";
        Map<String, Object> parameter = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path);
        return path;
    }


}
