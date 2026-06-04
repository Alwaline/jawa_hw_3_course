package ru.itis.shop.domains.orders.entity;

import jakarta.persistence.*;
import ru.itis.shop.domains.accounts.entity.Account;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Account owner;

    public Order() {
    }

    public Order(LocalDate date, Account owner) {
        this.date = date;
        this.owner = owner;
    }

    public Order(Long id, LocalDate date, Account owner) {
        this.id = id;
        this.date = date;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", owner=" + owner.getEmail() +
                '}';
    }
}
