package com.bingobucket.ticket.initializr;

import com.bingobucket.ticket.entity.Ticket;
import com.bingobucket.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class TicketDataInitializr {

    private final TicketRepository ticketRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void go() {

        String ticketGroupId = UUID.randomUUID().toString();
        var tickets = Flux.just(//TODO dynamically generate tickets
                  "*\t15\t22\t34\t*\t60\t69\t*\t*\n9\t*\t*\t37\t45\t52\t*\t*\t89\n*\t18\t*\t*\t*\t56\t65\t75\t83"
                , "10\t20\t*\t33\t50\t*\t*\t*\t81\n*\t*\t28\t39\t*\t57\t64\t71\t*\n4\t*\t*\t32\t*\t*\t66\t79\t82"
                , "*\t*\t26\t31\t*\t*\t68\t78\t87\n6\t*\t25\t35\t*\t53\t*\t*\t86\n8\t17\t*\t*\t49\t*\t70\t*\t90"
                , "5\t13\t27\t38\t41\t*\t*\t*\t*\n1\t12\t24\t*\t*\t*\t61\t80\t*\n*\t*\t23\t*\t48\t59\t62\t*\t84"
                , "*\t*\t21\t40\t46\t54\t*\t*\t85\n2\t14\t29\t*\t43\t*\t*\t74\t*\n7\t16\t*\t*\t44\t*\t67\t72\t*"
                , "*\t11\t*\t*\t42\t58\t63\t77\t*\n3\t*\t30\t36\t*\t55\t*\t73\t*\n*\t19\t*\t*\t47\t51\t*\t76\t88"
        )
                .map(value -> new Ticket(null, ticketGroupId, value))
                .flatMap(this.ticketRepository::save);


        this.ticketRepository
                .deleteAll()
                .thenMany(tickets)
                .thenMany(this.ticketRepository.findAll())
                .subscribe(log::info);
    }




}
