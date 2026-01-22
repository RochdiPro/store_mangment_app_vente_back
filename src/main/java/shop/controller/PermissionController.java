package shop.controller;


import org.springframework.web.bind.annotation.*;
import shop.model.RoutePermission;
import shop.service.PermissionService;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@CrossOrigin(origins = "*")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @GetMapping("/routes")
    public List<RoutePermission> getAllRoutes() {
        return service.getAvailableRoutes();
    }

    @GetMapping("/routes/category/{category}")
    public List<RoutePermission> getRoutesByCategory(@PathVariable String category) {
        return service.getRoutesByCategory(category);
    }

    @GetMapping("/default/{role}")
    public List<String> getDefaultPermissions(@PathVariable String role) {
        return service.getDefaultPermissions(role);
    }

    @GetMapping("/check")
    public boolean hasPermission(
            @RequestParam(required = false) List<String> userPermissions,
            @RequestParam String route,
            @RequestParam String userRole
    ) {
        return service.hasPermission(userPermissions, route, userRole);
    }

    @GetMapping("/category-label/{category}")
    public String getCategoryLabel(@PathVariable String category) {
        return service.getCategoryLabel(category);
    }
}

