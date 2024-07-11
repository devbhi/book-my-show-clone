package com.acciojob.bookmyshowapplications.Repository;

import com.acciojob.bookmyshowapplications.Models.Show;
import com.acciojob.bookmyshowapplications.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Integer> {

    public List<ShowSeat> findAllByShow(Show show); //Inbuilt method invoking

    //custom JPL Query
    @Query(nativeQuery = true,value = "select * from show_seats where show_show_id = :showId")
    public List<ShowSeat> findShowSeats(Integer showId);



}
