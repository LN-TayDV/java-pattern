package com.spring.ctx.domain.database.access.chapter09.transaction.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serial;
import java.time.LocalDate;

@Entity
@Table(name = "ALBUM")
@NamedQueries({
    @NamedQuery(name = Album.FIND_ALL, query = "select a from Album a where a.singer= :singer")
})
public class Album {

    public static final String FIND_ALL = "Album.findAll";
    @Serial
    private static final long serialVersionUID = 3L;
    @Id
    @Column(name = "ID")
    private String albumId;

    @Column
    private String title;

    @Column(name = "RELEASE_DATE")
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "SINGER_ID")
    private Singer singer;
}
