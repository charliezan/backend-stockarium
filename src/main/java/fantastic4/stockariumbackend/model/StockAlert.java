package fantastic4.stockariumbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock_alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private String message;

    private java.time.LocalDateTime timestamp;

    private Boolean resolved = false;
}
