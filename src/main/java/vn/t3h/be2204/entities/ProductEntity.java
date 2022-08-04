package vn.t3h.be2204.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "2204", catalog = "")
@Data
public class ProductEntity extends EntityBase{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto increment
    private Long id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "IMAGE")
    private String image;


    @Basic
    @Column(name = "PRICE")
    private Long price;

    @Basic
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Basic
    @Column(name = "BRAND_ID")
    private Long brandId;

    // biến tạm
    @Transient
    Long number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID", updatable = false, insertable = false)
    BrandEntity brandEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID", updatable = false, insertable = false)
    CategoryEntity categoryEntity;



}
