package cns.example.project_manage.repository;

import cns.example.project_manage.model.Projects;
import cns.example.project_manage.projection.ProjectReportProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {
    @Query(value = "select cast(id as varchar(20)) as id, name, introduce, TO_CHAR(start_date_time, 'yyyy-MM-dd') as startDate," +
            "TO_CHAR(end_date_time, 'yyyy-MM-dd') as endDate, " +
            "case when status=0 then 'PRE' when status=1 then 'START' when status=3 then 'END' else '---' end as status " +
            "from projects", nativeQuery = true)
    public List<ProjectReportProjection> getReports();
}
