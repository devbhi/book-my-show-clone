package com.acciojob.bookmyshowapplications.Models;

import com.acciojob.bookmyshowapplications.Enums.Genre;
import com.acciojob.bookmyshowapplications.Enums.Language;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(unique = true)
    private String movieName;

    private double duration;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private LocalDate releaseDate;

    private double rating;

    @Enumerated(value = EnumType.STRING)
    private Language language;

}
