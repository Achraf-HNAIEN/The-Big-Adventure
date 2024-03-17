package fr.uge.myproject.graphic;

public class Inventory {
    private final String[] items;
    private int selectedItemIndex = 0;

    public Inventory(int size) {
        items = new String[size];
    }

    public boolean addItem(String item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return true;
            }
        }
        return false;
    }
    public void removeItem(int index) {
        items[index] = null;
    }


    public String getItem(int index) {
        return items[index];
    }

    public void setItem(int index, String item) {
        items[index] = item;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int index) {
        this.selectedItemIndex = index;
    }

    public String[] getSlots() {
        return items;
    }

    public void selectNextItem() {
        selectedItemIndex = (selectedItemIndex + 1) % items.length;
    }

    public void selectPreviousItem() {
        selectedItemIndex = (selectedItemIndex - 1 + items.length) % items.length;
    }
}
