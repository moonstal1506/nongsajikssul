package com.nongsa.agriculture.model;

import com.nongsa.common.model.BaseEntity;
import com.nongsa.shop.model.Item;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Document extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String oriFileName;

    private String fileUrl;

    public void updateDocument(String oriFileName, String fileName, String fileUrl){
        this.oriFileName = oriFileName;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }

}
