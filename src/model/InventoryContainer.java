package model;

import java.util.ArrayList;
import java.util.List;

/**
 * En containerklasse, der håndterer en samling af {@link Inventory}-objekter.
 * Giver funktionalitet til at tilføje, fjerne, søge og hente inventarposter.
 */
public class InventoryContainer {

    /** Listen over alle inventarer i containeren. */
    private List<Inventory> inventories;

    /**
     * Opretter en ny, tom InventoryContainer.
     */
    public InventoryContainer() {
        inventories = new ArrayList<>();
    }

    /**
     * Tilføjer et nyt {@link Inventory} til containeren.
     *
     * @param inventory det inventarobjekt, der skal tilføjes
     */
    public void addInventory(Inventory inventory) {
        inventories.add(inventory);
    }

    /**
     * Fjerner et bestemt {@link Inventory} fra containeren.
     *
     * @param inventory det inventarobjekt, der skal fjernes
     * @return true hvis inventaret blev fundet og fjernet; false ellers
     */
    public boolean removeInventory(Inventory inventory) {
        return inventories.remove(inventory);
    }

    /**
     * Finder et {@link Inventory} ud fra dets tilknyttede {@link Product}.
     *
     * @param product produktet, der skal søges efter
     * @return det matchende Inventory, eller null hvis ingen blev fundet
     */
    public Inventory findByProduct(Product product) {
        for (Inventory inv : inventories) {
            if (inv.getProduct().equals(product)) {
                return inv;
            }
        }
        return null;
    }

    /**
     * Finder et {@link Inventory} baseret på både {@link Location} og {@link Product}.
     *
     * @param location lokationen, der skal søges efter
     * @param product produktet, der skal søges efter
     * @return det matchende Inventory, eller null hvis ingen blev fundet
     */
    public Inventory findByLocationAndProduct(Location location, Product product) {
        for (Inventory inv : inventories) {
            if (inv.getLocation().equals(location) &&
                inv.getProduct().equals(product)) {
                return inv;
            }
        }
        return null;
    }

    /**
     * Henter en liste over alle inventarer i containeren.
     * Den returnerede liste er en defensiv kopi.
     *
     * @return en ny liste med alle inventarer
     */
    public List<Inventory> getAllInventories() {
        return new ArrayList<>(inventories);
    }

    /**
     * Returnerer antallet af inventarobjekter i containeren.
     *
     * @return størrelsen på inventarlisten
     */
    public int size() {
        return inventories.size();
    }

}
