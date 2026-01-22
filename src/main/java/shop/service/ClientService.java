package shop.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.model.Client;
import shop.repository.ClientRepository;

import java.util.List;

@Service
@Transactional
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    // === getAll() ===
    public List<Client> getAll() {
        return repository.findAll();
    }

    // === getById() ===
    public Client getById(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    // === add() ===
    public Client add(Client client) {
        if (client.getSolde() == null) {
            client.setSolde(0.0);
        }
        if (client.getPoints() == null) {
            client.setPoints(0);
        }
        if (client.getCredit() == null) {
            client.setCredit(false);
        }
        return repository.save(client);
    }

    // === nameExists() ===
    public boolean nameExists(String nom, Long excludeId) {
        return repository.findAll().stream()
                .anyMatch(c ->
                        c.getNom().equalsIgnoreCase(nom)
                                && (excludeId == null || !c.getId().equals(excludeId))
                );
    }

    // === update() ===
    public Client update(Long id, Client client) {
        client.setId(id);
        return repository.save(client);
    }

    // === delete() ===
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // === updateSolde() ===
    public void updateSolde(Long clientId, Double montant) {
        Client client = getById(clientId);
        if (client != null) {
            client.setSolde(client.getSolde() + montant);
            repository.save(client);
        }
    }

    // === updatePoints() ===
    public void updatePoints(Long clientId, Integer points) {
        Client client = getById(clientId);
        if (client != null) {
            client.setPoints(client.getPoints() + points);
            repository.save(client);
        }
    }
}

