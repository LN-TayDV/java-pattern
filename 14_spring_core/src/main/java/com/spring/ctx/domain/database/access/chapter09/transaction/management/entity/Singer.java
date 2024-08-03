package com.spring.ctx.domain.database.access.chapter09.transaction.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serial;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;


@Entity
@Table(name = "SINGER")
@NamedQueries({
    @NamedQuery(name = Singer.FIND_ALL, query = "select s from Singer s"),
    @NamedQuery(name = Singer.COUNT_ALL, query = "select count(s) from Singer s")
})
@Data
public class Singer {

    public static final String FIND_ALL = "Singer.findAll";
    public static final String COUNT_ALL = "Singer.countAll";
    @Serial
    private static final long serialVersionUID = 2L;
    @Id
    @Column(name = "ID")
    private String singerId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "singer")
    private Set<Album> albums = new HashSet<>();

}