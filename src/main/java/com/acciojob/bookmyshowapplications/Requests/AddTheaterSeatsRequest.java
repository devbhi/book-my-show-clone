package com.acciojob.bookmyshowapplications.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTheaterSeatsRequest {

    private int noOfClassicSeats;
    private int noOfPremiumSeats;
    private int theaterId;


}
