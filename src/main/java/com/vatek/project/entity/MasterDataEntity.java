package com.catdev.project.entity;

import com.catdev.project.entity.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "master_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterDataEntity extends CommonEntity {

    @Column
    private String type;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private boolean active;

}
