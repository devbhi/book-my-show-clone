package com.acciojob.bookmyshowapplications.Requests;

import lombok.Data;

@Data
public class AddShowSeatsRequest {

    private Integer showId;
    private Integer priceOfClassicSeats;
    private Integer priceOfPremiumSeats;

}
