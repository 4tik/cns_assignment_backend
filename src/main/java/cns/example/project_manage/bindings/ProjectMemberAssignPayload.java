package cns.example.project_manage.bindings;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectMemberAssignPayload {
    private Long projectId;
    private Long userId;
    private Long currentUserId;
}
