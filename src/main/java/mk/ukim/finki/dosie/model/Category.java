package mk.ukim.finki.dosie.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.dosie.model.enums.ReportType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String description;
    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    public Category(String description, ReportType reportType) {
        this.description = description;
        this.reportType = reportType;
    }
}
