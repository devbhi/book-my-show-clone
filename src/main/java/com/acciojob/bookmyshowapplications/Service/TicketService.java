package com.acciojob.bookmyshowapplications.Service;

import com.acciojob.bookmyshowapplications.Exceptions.SeatUnavailableException;
import com.acciojob.bookmyshowapplications.Models.Movie;
import com.acciojob.bookmyshowapplications.Models.Show;
import com.acciojob.bookmyshowapplications.Models.ShowSeat;
import com.acciojob.bookmyshowapplications.Models.Theater;
import com.acciojob.bookmyshowapplications.Models.Ticket;
import com.acciojob.bookmyshowapplications.Models.User;
import com.acciojob.bookmyshowapplications.Repository.MovieRepository;
import com.acciojob.bookmyshowapplications.Repository.ShowRepository;
import com.acciojob.bookmyshowapplications.Repository.ShowSeatRepository;
import com.acciojob.bookmyshowapplications.Repository.TheaterRepository;
import com.acciojob.bookmyshowapplications.Repository.TicketRepository;
import com.acciojob.bookmyshowapplications.Repository.UserRepository;
import com.acciojob.bookmyshowapplications.Requests.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket bookTicket(BookTicketRequest bookTicketRequest) throws Exception{

        //1. Calculate the total cost of the tickets

        Movie movie = movieRepository.findMovieByMovieName(bookTicketRequest.getMovieName());
        Theater theater = theaterRepository.findById(bookTicketRequest.getTheaterId()).get();

        //1.1 Find the ShowEntity with this date and Time
        Show show = showRepository.findShowByShowDateAndShowTimeAndMovieAndTheater(bookTicketRequest.getShowDate(), bookTicketRequest.getShowTime(), movie, theater);


        Integer showId = show.getShowId();
        List<ShowSeat> showSeatList = showSeatRepository.findShowSeats(showId);

        //Calculate the total Amt and if all seats mentioned are available or not
        int totalAmount = 0;
        Boolean areAllSeatsAvailable = Boolean.TRUE;

        for(String seatNo:bookTicketRequest.getRequestedSeats()) {
            for(ShowSeat showSeat:showSeatList) {
                if(showSeat.getSeatNo().equals(seatNo))
                {
                    if(showSeat.getIsAvailable()==Boolean.FALSE){
                        areAllSeatsAvailable = Boolean.FALSE;
                        break;
                    }
                    totalAmount = totalAmount+showSeat.getPrice();
                }
            }
        }

        if(areAllSeatsAvailable==Boolean.FALSE){
            throw new SeatUnavailableException("The requested Seats are unavailable");
        }


        //2. Make the seats booked:(Only if seats are available : otherwise throw exception)

        for(String seatNo:bookTicketRequest.getRequestedSeats()) {
            for(ShowSeat showSeat:showSeatList) {
                if(showSeat.getSeatNo().equals(seatNo))
                {
                    showSeat.setIsAvailable(Boolean.FALSE);
                }
            }
        }

        User user = userRepository.findUserByMobNo(bookTicketRequest.getMobNo());

        //3. Save the ticketEntity

        Ticket ticket = Ticket.builder().user(user)
                .movieName(bookTicketRequest.getMovieName())
                .showDate(bookTicketRequest.getShowDate())
                .theaterNameAndAddress(theater.getName()+" "+theater.getAddress())
                .showTime(bookTicketRequest.getShowTime())
                .totalAmtPaid(totalAmount)
                .build();

        ticket = ticketRepository.save(ticket);

        //4. Generate and return back the actual ticket response

        return ticket;


    }

}
