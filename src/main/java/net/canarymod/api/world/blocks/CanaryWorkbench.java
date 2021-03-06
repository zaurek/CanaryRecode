package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.Dimension;
import net.minecraft.server.OContainerWorkbench;
import net.minecraft.server.OItemStack;

public class CanaryWorkbench implements Workbench{
    
    private OContainerWorkbench container;

    public CanaryWorkbench(OContainerWorkbench container) {
        this.container = container;
    }

    @Override
    public Inventory getInventory() {
        return new CanaryInventory(container.a);
    }
    
    @Override
    public Block getBlock() {
        return getDimension().getBlockAt(getX(), getY(), getZ());
    }

    @Override
    public int getX(){
        return container.h;
    }
    
    @Override
    public int getY(){
        return container.i;
    }
    
    @Override
    public int getZ(){
        return container.j;
    }
    
    @Override
    public Dimension getDimension(){
        return container.c.getCanaryDimension();
    }
    
    @Override
    public void update(){
        container.b.update();
    }

    @Override
    public void clearContents() {
        container.a.clearContents();
    }

    @Override
    public CanaryItem decreaseItemStackSize(int itemId, int amount) {
        OItemStack item = container.a.decreaseItemStackSize(itemId, amount);
        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public CanaryItem[] getContents() {
        OItemStack[] oStacks = container.a.getContents();
        CanaryItem[] items = new CanaryItem[oStacks.length];
        for(int i = 0; i < oStacks.length; i++) {
            items[i] = new CanaryItem(oStacks[i]);
        }
        
        return items;
    }

    @Override
    public String getInventoryName() {
        return container.a.getInventoryName();
    }

    @Override
    public int getInventorySize() {
        return container.a.getInventorySize();
    }

    @Override
    public int getInventoryStackLimit() {
        return container.a.getInventoryStackLimit();
    }

    @Override
    public Item getItem(int id) {
        return container.a.getItem(id);
    }

    @Override
    public Item getItem(int id, int amount) {
       return container.a.getItem(id, amount);
    }

    @Override
    public CanaryItem getSlot(int index) {
        OItemStack item = container.a.getSlot(index);
        return item != null ? item.getCanaryItem() : null;
    }

    @Override
    public boolean hasItem(int itemId) {
        return container.a.hasItem(itemId);
    }

    @Override
    public boolean hasItemStack(Item item) {
       return container.a.hasItemStack(((CanaryItem) item).getHandle());
    }

    @Override
    public Item removeItem(Item item) {
        return container.a.removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return container.a.removeItem(id);
    }

    @Override
    public void setContents(Item[] items) {
        OItemStack[] oStacks = new OItemStack[items.length];
        for(int i = 0; i < items.length; i++) {
            if(items[i] != null) {
                oStacks[i] = ((CanaryItem)items[i]).getHandle();
            }
            else {
                oStacks[i] = null;
            }
        }
        container.a.setContents(oStacks);
    }

    @Override
    public void setInventoryName(String value) {
        container.a.setInventoryName(value);
    }

    @Override
    public void setSlot(int index, Item item) {
        container.a.setSlot(index, ((CanaryItem) item).getHandle());
    }
    
    public OContainerWorkbench getHandle(){
        return container;
    }

    @Override
    public void addItem(int itemId, int amount) {
        container.a.addItem(itemId, amount);
    }

    @Override
    public void addItem(Item item) {
        container.a.addItem(item);
    }

    @Override
    public int getEmptySlot() {
        return container.a.getEmptySlot();
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return container.a.hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return container.a.hasItemStack(itemId, minAmount, maxAmount);
    }
}
