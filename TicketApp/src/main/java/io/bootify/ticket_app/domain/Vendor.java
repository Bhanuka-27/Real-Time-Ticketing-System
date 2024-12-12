package io.bootify.ticket_app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.util.Set;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "Vendors")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {

    //Constructor to initialize a Vendor with just a name.
    public Vendor(String name) {
        this.name = name;
    }

    // Unique identifier for each vendor.
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The name of the vendor.
    @Column(nullable = false)
    private String name;

    // Set of tickets associated with this vendor.
    @OneToMany(mappedBy = "vendorId")
    private Set<Ticket> tickets;

    // The total number of tickets managed by the vendor.
    @Column
    private Integer totalTickets;

    // The rate at which the vendor releases tickets.
    @Column
    private Integer ticketReleaseRate;

    // The maximum number of tickets the vendor can manage at any given time.
    @Column
    private Integer maxTicketCapacity;

    // Timestamp of when the vendor was created.
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    // Timestamp of the last update to the vendor record.
    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
