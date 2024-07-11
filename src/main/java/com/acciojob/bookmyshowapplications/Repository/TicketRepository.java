package com.acciojob.bookmyshowapplications.Repository;

import com.acciojob.bookmyshowapplications.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,String> {
}
