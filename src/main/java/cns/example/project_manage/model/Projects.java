package cns.example.project_manage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "projects")
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String introduce;
    private int status;
    private Date startDateTime;
    private Date endDateTime;

    @Transient
    private List<ProjectMember> projectMembers;

    private Date createdOn;
    private Long createdBy;
    private Date updatedOn;
    private Long updatedBy;
}
