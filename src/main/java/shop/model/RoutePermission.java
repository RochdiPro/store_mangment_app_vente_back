package shop.model;



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutePermission {
    private String path;
    private String label;
    private String category; // gestion | operations | credits | systeme
}
