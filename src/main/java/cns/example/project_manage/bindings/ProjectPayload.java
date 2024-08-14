package cns.example.project_manage.bindings;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectPayload {
    private String name;
    private String introduce;
    private int status;
    private Date startDateTime;
    private Date endDateTime;
    private Long userId;
    private List<Long> users;
}
