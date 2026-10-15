package com.example.helpdesk.repository;

import com.example.helpdesk.dto.request.TicketDto;
import com.example.helpdesk.dto.response.CustomerCountDto;
import com.example.helpdesk.dto.response.TicketExDto;
import com.example.helpdesk.dto.response.TicketInfoDto;
import com.example.helpdesk.dto.response.TicketRespDto;
import com.example.helpdesk.enums.Priority;
import com.example.helpdesk.enums.Status;
import com.example.helpdesk.model.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
   /* List<Ticket> findByStatus(Status status); // select t from Ticket t where t.status=?1
    List<Ticket> findByPriorityAndStatus(Priority priority, Status status);
    // select t from Ticket t where t.priority=?1 and t.status=?2

    */
    @Query("""
            select new com.example.helpdesk.dto.response.TicketInfoDto(t.id,t.subject,
            t.createdAt,t.priority,t.status,c.name,e.name,e.email)
            from Ticket t
            JOIN t.customer c
            JOIN t.executive e
            where c.id = ?1
            """)
    List<TicketInfoDto> getTicketsByCustomerId(Long customerId, Pageable pageable);

    @Query("""
            select new com.example.helpdesk.dto.response.TicketInfoDto(t.id,t.subject,
            t.createdAt,t.priority,t.status,c.name,e.name,e.email)
            from Ticket t
            JOIN t.customer c
            JOIN t.executive e
            JOIN c.user u
            where u.username = ?1
            """)
    List<TicketInfoDto> getTicketsByCustomerUsername(String customerUsername, Pageable pageable);

    @Query("""
select new com.example.helpdesk.dto.response.TicketExDto(t.id,e.id,e.name,t.subject,t.priority,
t.status,t.createdAt,c.id,c.name )
from Ticket t 
join t.executive e
join t.customer c
join e.user u
where u.username=?1

""")
    List<TicketExDto> getTicketsByExecutiveUsername(String executiveUsername, Integer page, Integer size);

    @Query("""
select t.status as ticketStaust,count(c.id) as customerCount
from Ticket t
join t.customer c
group by t.status
""")
    List<CustomerCountDto> getCustomerCount();

@Query("""
select t from Ticket t where t.priority=?1
""")
    List<Ticket> findByPriority(Priority priority);

    @Query("""
            select t.id as ticketId, t.subject as subject, t.createdAt as createdAt, t.priority as priority, t.status as status
            from Ticket t
            where (?1 IS NULL OR t.priority = ?1)
            AND  (?2 IS NULL OR t.status = ?2)
            AND (?3 IS NULL OR t.createdAt >= ?3)
            AND (?4 IS NULL OR t.createdAt <= ?4)
            """)
    List<TicketRespDto> dynamicFilterTicket(Priority priority, Status status, Instant instant, Instant instant1);
}
