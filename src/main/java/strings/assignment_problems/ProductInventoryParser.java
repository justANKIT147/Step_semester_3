package main.java.strings.assignment_problems;

/**
 * Week 2 - Problem 3: Product Inventory CSV Parser
 * Parses CSV lines in the format: ProductName,SKU,Quantity.
 */
public class ProductInventoryParser {

    /**
     * Parses a CSV record and prints the formatted inventory entry or an error message.
     *
     * @param csvLine CSV string representing one inventory record
     */
    public static void parseInventoryRecord(String csvLine) {
        if (csvLine == null) {
            System.out.println("Invalid Record");
            return;
        }

        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String productName = fields[0].trim();
        String sku = fields[1].trim();
        String quantity = fields[2].trim();

        if (productName.isEmpty() || sku.isEmpty() || quantity.isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Product: " + productName + " | SKU: " + sku + " | Qty: " + quantity);
    }

    public static void main(String[] args) {
        // Examples from assignment
        parseInventoryRecord("Wireless Mouse,WM-2201,150");
        parseInventoryRecord("Wireless Mouse,150");

        // Edge case
        parseInventoryRecord("Mechanical Keyboard,MK-88,75");
        parseInventoryRecord(",,");
    }
}
