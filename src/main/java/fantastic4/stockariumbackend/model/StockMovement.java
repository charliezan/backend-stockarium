package fantastic4.stockariumbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock_movements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Integer quantityChange; // puede ser positivo o negativo
    private String reason; // "Compra", "Venta", "Ajuste", etc.
    private java.time.LocalDateTime date;
}

