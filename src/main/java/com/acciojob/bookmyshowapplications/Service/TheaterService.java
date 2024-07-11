package com.acciojob.bookmyshowapplications.Service;

import com.acciojob.bookmyshowapplications.Enums.SeatType;
import com.acciojob.bookmyshowapplications.Models.Theater;
import com.acciojob.bookmyshowapplications.Models.TheaterSeat;
import com.acciojob.bookmyshowapplications.Repository.TheaterRepository;
import com.acciojob.bookmyshowapplications.Repository.TheaterSeatRepository;
import com.acciojob.bookmyshowapplications.Requests.AddTheaterRequest;
import com.acciojob.bookmyshowapplications.Requests.AddTheaterSeatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterSeatRepository theaterSeatRepository;

    public String addTheater(AddTheaterRequest addTheaterRequest){

        //Convert this AddRequest to an Entity

        //how to do this ??

        //Use a constructor to create an object : Generally constructors
        //are not available

//        Theater theater = new Theater(); //We have this entity object
//        theater.setName(addTheaterRequest.getName());
//        theater.setAddress(addTheaterRequest.getAddress());
//        theater.setNoOfScreens(addTheaterRequest.getNoOfScreens());

        //Modern way of creating the object is

        Theater theater = Theater.builder()
                .address(addTheaterRequest.getAddress())
                .noOfScreens(addTheaterRequest.getNoOfScreens())
                .name(addTheaterRequest.getName())
                .build();

        //Save the entity to the DB

        theater = theaterRepository.save(theater);
        return "The theater is with a theaterId "+theater.getTheaterId();
    }


    public String addTheaterSeats(AddTheaterSeatsRequest addTheaterSeatsRequest){

        int noOfClassicSeats = addTheaterSeatsRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = addTheaterSeatsRequest.getNoOfPremiumSeats();

        Integer theaterId = addTheaterSeatsRequest.getTheaterId();
        Theater theater = theaterRepository.findById(theaterId).get();

        int classicSeatCounter = 1;
        char ch='A';
        int rowNo = 1;
        List<TheaterSeat> theaterSeatList = new ArrayList<>();
        while(classicSeatCounter<=noOfClassicSeats) {

            String seatNo = rowNo+""+ch;
            TheaterSeat theaterSeat = TheaterSeat.builder()
                                    .seatNo(seatNo)
                                    .seatType(SeatType.CLASSIC)
                                    .theater(theater)
                                    .build();

            theaterSeatList.add(theaterSeat);
            ch++;
            if(classicSeatCounter%5==0) {
                rowNo = rowNo+1;
                ch = 'A';
            }
            classicSeatCounter++;
        }
        int premiumSeatCounter = 1;
        ch='A';

        if(classicSeatCounter%5!=1)
            rowNo = rowNo+1;


        while(premiumSeatCounter<=noOfPremiumSeats){

            String seatNo = rowNo+""+ch;
            TheaterSeat theaterSeat = TheaterSeat.builder()
                    .seatNo(seatNo)
                    .theater(theater) //Setting the unidirectional
                    .seatType(SeatType.PREMIUM)
                    .build();

            theaterSeatList.add(theaterSeat);
            ch++;
            if(premiumSeatCounter%5==0) {
                rowNo = rowNo+1;
                ch = 'A';
            }
            premiumSeatCounter++;
        }

        theater.setTheaterSeatList(theaterSeatList);
        theaterRepository.save(theater);

        //Theater seats will get automatically saved
        //bcz of cascading property written in the parent table
        return "Theater seats have been generated";
    }

}
