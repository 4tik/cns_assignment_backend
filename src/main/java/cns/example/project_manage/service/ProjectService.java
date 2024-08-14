package cns.example.project_manage.service;

import cns.example.project_manage.bindings.ProjectPayload;
import cns.example.project_manage.model.Projects;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ProjectService {
    public List<Projects> getProjects();
    public List<Projects> getProjectsByUserId(Long userId);
    public void store(ProjectPayload projectPayload);
    public Projects getById(Long id);
    public void updateById(Long id, ProjectPayload projectPayload);
    public void deleteById(Long id);
    public String getProjectsReport() throws IOException, JRException;
}
