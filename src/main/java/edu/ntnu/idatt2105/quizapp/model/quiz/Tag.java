package edu.ntnu.idatt2105.quizapp.model.quiz;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class for tags. Tags are a type of object which gives further explanation
 * to a quiz.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 * @since 2024-03-31
 */
@Entity
@Table(name = "tags")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "description", nullable = false)
  private String description;
}
