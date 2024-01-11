package com.Yassine_amine;

import java.util.HashMap;
import java.util.Map;

import com.Produit;

public class ProduitService {
    private Map<Long, Produit> produits;

    public ProduitService() {
        this.produits = new HashMap<>();
    }

        public void ajouterProduit(Produit produit) throws ProduitException {
        validerUniciteProduit(produit);
        validerDonneesProduit(produit);
        produits.put(produit.getId(), produit);
    }

    public Produit trouverProduitParId(Long id) throws ProduitNotFoundException {
        Produit produit = produits.get(id);
        if (produit == null) {
            throw new ProduitNotFoundException();
        }
        return produit;
    }

    public void mettreAJourProduit(Produit produit) throws ProduitException, ProduitNotFoundException {
        if (!produits.containsKey(produit.getId())) {
            throw new ProduitNotFoundException();
        }
        validerDonneesProduit(produit);
        produits.put(produit.getId(), produit);
    }

    public void supprimerProduit(Long id) throws ProduitNotFoundException {
        if (!produits.containsKey(id)) {
            throw new ProduitNotFoundException();
        }
        produits.remove(id);
    }

    private void validerUniciteProduit(Produit produit) throws ProduitException {
        if (produits.values().stream().anyMatch(p -> p.getId().equals(produit.getId()) || p.getNom().equals(produit.getNom()))) {
            throw new ProduitException();
        }
    }

    private void validerDonneesProduit(Produit produit) throws ProduitException {
        if (produit.getPrix() <= 0 || produit.getQuantite() <= 0) {
            throw new ProduitException();
        }
    }
}
