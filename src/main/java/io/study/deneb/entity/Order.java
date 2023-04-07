package io.study.deneb.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "order_detail")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;


  // one-to-one relationship with consumer
  @OneToOne(
    mappedBy = "order",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    orphanRemoval = true)
  private Consumer consumer;

  @OneToMany(mappedBy = "order",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    orphanRemoval = true)
  private Set<OrderItem> items;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Set<OrderState> state;

  @Column(columnDefinition = "text")
  private String notes;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;


  public Order() { }

  public Order(String notes) {
    this.notes = notes;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Consumer getConsumer() {
    return consumer;
  }

  public void setConsumer(Consumer consumer) {
    this.consumer = consumer;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  public void setItems(Set<OrderItem> items) {
    this.items = items;
  }

  public Set<OrderState> getState() {
    return state;
  }

  public void setState(Set<OrderState> state) {
    this.state = state;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
