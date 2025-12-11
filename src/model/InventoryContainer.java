package model;

import java.util.ArrayList;
import java.util.List;

/**
 * En Singleton-container, der håndterer en samling af {@link Inventory}-objekter.
 * Klassen giver mulighed for at tilføje, fjerne og søge i inventarlisten.
 */
public class InventoryContainer {

    /** Den statiske instans af InventoryContainer (Singleton). */
    private static InventoryContainer instance;

    /** Listen over alle inventarposter i containeren. */
    private List<Inventory> inventories;

    /**
     * Privat constructor for at sikre Singleton-designmønsteret.
     * Opretter en tom liste af inventarer.
     */
    private InventoryContainer() {
        inventories = new ArrayList<>();
    }

    /**
     * Returnerer den globale Singleton-instans af InventoryContainer.
     * Hvis instansen ikke eksisterer, oprettes den.
     *
     * @return den eneste instans af InventoryContainer
     */
    public static InventoryContainer getInstance() {
        if (instance == null) {
            instance = new InventoryContainer();
        }
        return instance;
    }

    /**
     * Tilføjer et nyt {@link Inventory} til containeren.
     *
     * @param inventory inventarobjektet, der skal tilføjes
     * @return 
     */
    public boolean addInventory(Inventory inventory) {
        for (Inventory existingInventory : inventories) {
            if (existingInventory.getProduct().getSKU().equals(inventory.getProduct().getSKU())) {
                System.out.println("Fejl: Lager for dette produkt findes allerede.");
                return false;
            }
        }
        
        inventories.add(inventory);
        return true;
    }

    /**
     * Fjerner et givent {@link Inventory} fra containeren.
     *
     * @param inventory inventarobjektet, der ønskes fjernet
     * @return true hvis objektet blev fjernet; false ellers
     */
    public boolean removeInventory(Inventory inventory) {
        return inventories.remove(inventory);
    }

    /**
     * Finder et inventar baseret på et bestemt {@link Product}.
     *
     * @param product produktet, der søges efter
     * @return det matchende Inventory, eller null hvis intet findes
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
     * Finder et inventar baseret på både lokation og produkt.
     *
     * @param location den ønskede lokation
     * @param product det ønskede produkt
     * @return det matchende Inventory, eller null hvis intet findes
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
     * Returnerer en kopi af alle inventarer i containeren.
     *
     * @return en ny liste med alle Inventory-objekter
     */
    public ArrayList<Inventory> getLowStockInventories() {
        ArrayList<Inventory> lowStockList = new ArrayList<>();
        for (Inventory inv : inventories) {
            if (inv.isStockLow()) {
                lowStockList.add(inv);
            }
        }
        return lowStockList;
    }

    /**
     * Returnerer antallet af inventarposter i containeren.
     *
     * @return antal Inventory-objekter
     */
    public int size() {
        return inventories.size();
    }

}
