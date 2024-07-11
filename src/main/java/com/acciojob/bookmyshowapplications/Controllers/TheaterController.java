package com.acciojob.bookmyshowapplications.Controllers;

import com.acciojob.bookmyshowapplications.Requests.AddTheaterRequest;
import com.acciojob.bookmyshowapplications.Requests.AddTheaterSeatsRequest;
import com.acciojob.bookmyshowapplications.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;


    @PostMapping("addTheater")
    public String addTheater(@RequestBody AddTheaterRequest addTheaterRequest) {

        String result = theaterService.addTheater(addTheaterRequest);
        return result;
    }

    @PostMapping("addTheaterSeats")
    public String addTheaterSeats(@RequestBody AddTheaterSeatsRequest addTheaterSeatsRequest){

        String result = theaterService.addTheaterSeats(addTheaterSeatsRequest);
        return result;

    }

    //Add Theater Seats
}
