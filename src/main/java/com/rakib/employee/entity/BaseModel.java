package com.rakib.employee.entity;

import com.rakib.employee.enums.ActiveStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer activeStatus;

    @PrePersist
    public void setPreInsertData() {
        this.activeStatus = ActiveStatus.ACTIVE.getValue();
    }

    @PreUpdate
    public void setPreUpdateData() {
        if(this.activeStatus == null) {
            this.activeStatus = ActiveStatus.ACTIVE.getValue();
        }
        if (this.activeStatus != ActiveStatus.DELETE.getValue()) {
            this.activeStatus = ActiveStatus.ACTIVE.getValue();
        }

    }

}
