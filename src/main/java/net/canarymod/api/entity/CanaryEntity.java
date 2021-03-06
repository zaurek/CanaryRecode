package net.canarymod.api.entity;

import net.canarymod.api.world.CanaryDimension;
import net.canarymod.api.world.Dimension;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Vector3D;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityLiving;

public class CanaryEntity implements Entity {

    protected OEntity entity;
    
    public CanaryEntity(OEntity entity) {
        this.entity = entity;
    }
    
    public OEntity getHandle() {
        return entity;
    }
    
    @Override
    public double getX() {
        return entity.bm;
    }

    @Override
    public double getY() {
        return entity.bn;
    }

    @Override
    public double getZ() {
        return entity.bo;
    }

    @Override
    public double getMotionX() {
        return entity.bp;
    }

    @Override
    public double getMotionY() {
        return entity.bq;
    }

    @Override
    public double getMotionZ() {
        return entity.br;
    }

    @Override
    public float getPitch() {
        return entity.bt;
    }

    @Override
    public float getRotation() {
        return entity.bs;
    }

    @Override
    public void setX(double x) {
        this.entity.bm = x;
    }

    @Override
    public void setX(int x) {
        this.entity.bm = x;

    }

    @Override
    public void setY(double y) {
        this.entity.bn = y;

    }

    @Override
    public void setY(int y) {
        this.entity.bn = y;

    }

    @Override
    public void setZ(double z) {
        this.entity.bo = z;
    }

    @Override
    public void setZ(int z) {
        this.entity.bo = z;
    }

    @Override
    public void setMotionX(double motionX) {
        entity.bp = motionX;
        entity.bA = true;
    }

    @Override
    public void setMotionY(double motionY) {
        entity.bq = motionY;
        entity.bA = true;
    }

    @Override
    public void setMotionZ(double motionZ) {
        entity.br = motionZ;
        entity.bA = true;
    }

    @Override
    public void setPitch(float pitch) {
        entity.br = pitch;

    }

    @Override
    public void setRotation(float rotation) {
        entity.bt = rotation;

    }

    @Override
    public void setDimension(Dimension dim) {
        this.entity.setDimension((CanaryDimension) dim);
    }

    @Override
    public Dimension getDimension() {
        return entity.getDimension();
    }
    
    @Override
    public World getWorld() {
        return entity.getDimension().getWorld();
    }

    @Override
    public void setFireTicks(int ticks) {
        this.entity.c = ticks;

    }
    
    @Override
    public int getFireTicks() {
        return this.entity.c;
    }

    @Override
    public boolean isLiving() {
        return entity instanceof OEntityLiving;
    }

    @Override
    public boolean isItem() {
        return entity instanceof OEntityItem;
    }

    @Override
    public EntityItem dropLoot(int itemId, int amount) {
        return new CanaryEntityItem(entity.b(itemId, amount));
    }

    @Override
    public boolean isSprinting() {
        return entity.aZ();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        entity.h(sprinting);
    }

    @Override
    public boolean isSneaking() {
        return entity.aY();
    }

    @Override
    public void setSneaking(boolean sneaking) {
        entity.g(sneaking);
        
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public Vector3D getPosition() {
        return new Vector3D(getX(), getY(), getZ());
    }

    @Override
    public Location getLocation() {
        return new Location(getDimension(), getX(), getY(), getZ(), getPitch(), getRotation());
    }

}
