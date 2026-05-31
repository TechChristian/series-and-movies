package br.com.api.techchristian.series.database.models;

import br.com.api.techchristian.series.database.enums.ContentTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String genre;

    @Enumerated(EnumType.STRING) // -> Jpa save movies or series.
    @Column(name = "type", nullable = false)
    private ContentTypeEnum contentType;

    @Column(nullable = false)
    private Integer releaseYear;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
