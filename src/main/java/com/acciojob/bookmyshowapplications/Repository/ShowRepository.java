package com.acciojob.bookmyshowapplications.Repository;

import com.acciojob.bookmyshowapplications.Models.Movie;
import com.acciojob.bookmyshowapplications.Models.Show;
import com.acciojob.bookmyshowapplications.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ShowRepository extends JpaRepository<Show,Integer> {

    public Show findShowByShowDateAndShowTimeAndMovieAndTheater(LocalDate showDate,
                                                                LocalTime showTime,
                                                                Movie movie,
                                                                Theater theater);

}
