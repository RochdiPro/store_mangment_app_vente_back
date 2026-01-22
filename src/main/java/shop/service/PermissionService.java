package shop.service;



import org.springframework.stereotype.Service;
import shop.model.RoutePermission;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    // Routes disponibles (comme Angular)
    private final List<RoutePermission> availableRoutes = List.of(
            new RoutePermission("dashboard", "Tableau de bord", "systeme"),
            new RoutePermission("clients", "Clients", "gestion"),
            new RoutePermission("fournisseurs", "Fournisseurs", "gestion"),
            new RoutePermission("telephones", "Téléphones", "gestion"),
            new RoutePermission("accessoires", "Accessoires", "gestion"),
            new RoutePermission("pieces-rechange", "Pièces de rechange", "gestion"),
            new RoutePermission("inventaire", "Inventaire", "gestion"),
            new RoutePermission("ventes", "Ventes", "operations"),
            new RoutePermission("achats", "Achats", "operations"),
            new RoutePermission("reparations", "Réparations", "operations"),
            new RoutePermission("paiements", "Paiements Télécom", "operations"),
            new RoutePermission("caisses", "Caisses", "operations"),
            new RoutePermission("credits-clients", "Crédits Clients", "credits"),
            new RoutePermission("credits-fournisseurs", "Crédits Fournisseurs", "credits"),
            new RoutePermission("historique", "Historique", "systeme"),
            new RoutePermission("configuration", "Configuration", "systeme"),
            new RoutePermission("utilisateurs", "Utilisateurs", "systeme"),
            new RoutePermission("data-management", "Gestion des données", "systeme")
    );

    // Permissions par rôle par défaut (comme Angular)
    private final Map<String, List<String>> defaultPermissionsByRole = Map.of(
            "ADMIN", List.of("dashboard", "clients", "fournisseurs", "telephones", "accessoires",
                    "pieces-rechange", "inventaire", "ventes", "achats", "reparations", "paiements",
                    "caisses", "credits-clients", "credits-fournisseurs", "historique",
                    "configuration", "utilisateurs", "data-management"),
            "VENDEUR", List.of("dashboard", "clients", "telephones", "accessoires", "inventaire",
                    "ventes", "paiements", "credits-clients"),
            "TECHNICIEN", List.of("dashboard", "clients", "reparations", "pieces-rechange", "inventaire")
    );

    // === Toutes les routes ===
    public List<RoutePermission> getAvailableRoutes() {
        return availableRoutes;
    }

    // === Routes par catégorie ===
    public List<RoutePermission> getRoutesByCategory(String category) {
        return availableRoutes.stream()
                .filter(route -> route.getCategory() != null &&
                        route.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // === Permissions par défaut pour un rôle ===
    public List<String> getDefaultPermissions(String role) {
        return defaultPermissionsByRole.getOrDefault(role, List.of());
    }

    // === Vérifier si un utilisateur a la permission ===
    public boolean hasPermission(List<String> userPermissions, String route, String userRole) {
        if ("SUP_ADMIN".equalsIgnoreCase(userRole)) return true; // Super admin tout a accès
        if ("dashboard".equalsIgnoreCase(route)) return true;     // Dashboard accessible à tous
        if (userPermissions == null || userPermissions.isEmpty()) {
            return defaultPermissionsByRole.getOrDefault(userRole, List.of())
                    .contains(route);
        }
        return userPermissions.contains(route);
    }

    // === Label lisible pour une catégorie ===
    public String getCategoryLabel(String category) {
        return switch (category.toLowerCase()) {
            case "gestion" -> "Gestion";
            case "operations" -> "Opérations";
            case "credits" -> "Crédits";
            case "systeme" -> "Système";
            default -> category;
        };
    }
}

