package model;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

public class InventoryContainer {

    private List<Inventory> inventories;

    public InventoryContainer() {
        inventories = new ArrayList<>();
    }

    public void addInventory(Inventory inventory) {
        inventories.add(inventory);
    }

    public boolean removeInventory(Inventory inventory) {
        return inventories.remove(inventory);
    }

    public Inventory findByProduct(Product product) {
        for (Inventory inv : inventories) {
            if (inv.getProduct().equals(product)) {
                return inv;
            }
        }
        return null;
    }

    public Inventory findByLocationAndProduct(Location location, Product product) {
        for (Inventory inv : inventories) {
            if (inv.getLocation().equals(location) &&
                inv.getProduct().equals(product)) {
                return inv;
            }
        }
        return null;
    }

    public List<Inventory> getAllInventories() {
        return new ArrayList<>(inventories);
    }

    public int size() {
        return inventories.size();
    }
=======
public class InventoryContainer {

>>>>>>> e50e8c7b2f7f6b982d4c0a8932dee1ba66483375
}
