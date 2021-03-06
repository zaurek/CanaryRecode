package net.canarymod.api.world;

import java.util.ArrayList;

import net.canarymod.api.CanaryEntityTracker;
import net.canarymod.api.CanaryPlayerManager;
import net.canarymod.api.EntityTracker;
import net.canarymod.api.Particle;
import net.canarymod.api.PlayerManager;
import net.canarymod.api.entity.CanaryBlaze;
import net.canarymod.api.entity.CanaryChicken;
import net.canarymod.api.entity.CanaryCow;
import net.canarymod.api.entity.CanaryCreeper;
import net.canarymod.api.entity.CanaryEnderman;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.CanaryGhast;
import net.canarymod.api.entity.CanaryGiantZombie;
import net.canarymod.api.entity.CanaryLavaSlime;
import net.canarymod.api.entity.CanaryMushroomCow;
import net.canarymod.api.entity.CanaryOcelot;
import net.canarymod.api.entity.CanaryPig;
import net.canarymod.api.entity.CanaryPigZombie;
import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.entity.CanarySheep;
import net.canarymod.api.entity.CanarySilverfish;
import net.canarymod.api.entity.CanarySkeleton;
import net.canarymod.api.entity.CanarySlime;
import net.canarymod.api.entity.CanarySpider;
import net.canarymod.api.entity.CanarySquid;
import net.canarymod.api.entity.CanaryVillager;
import net.canarymod.api.entity.CanaryWolf;
import net.canarymod.api.entity.CanaryZombie;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.EntityAnimal;
import net.canarymod.api.entity.EntityAnimal.AnimalType;
import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.EntityMob;
import net.canarymod.api.entity.EntityMob.MobType;
import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.api.world.blocks.CanaryBrewingStand;
import net.canarymod.api.world.blocks.CanaryChest;
import net.canarymod.api.world.blocks.CanaryDispenser;
import net.canarymod.api.world.blocks.CanaryFurnace;
import net.canarymod.api.world.blocks.CanaryJukebox;
import net.canarymod.api.world.blocks.CanaryMobSpawner;
import net.canarymod.api.world.blocks.CanaryNoteBlock;
import net.canarymod.api.world.blocks.CanarySign;
import net.canarymod.api.world.blocks.Chest;
import net.canarymod.api.world.blocks.ComplexBlock;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Vector3D;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityBlaze;
import net.minecraft.server.OEntityChicken;
import net.minecraft.server.OEntityCow;
import net.minecraft.server.OEntityCreeper;
import net.minecraft.server.OEntityEnderman;
import net.minecraft.server.OEntityGhast;
import net.minecraft.server.OEntityGiantZombie;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityLavaSlime;
import net.minecraft.server.OEntityLightningBolt;
import net.minecraft.server.OEntityMooshroom;
import net.minecraft.server.OEntityOcelot;
import net.minecraft.server.OEntityPig;
import net.minecraft.server.OEntityPigZombie;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OEntitySheep;
import net.minecraft.server.OEntitySilverfish;
import net.minecraft.server.OEntitySkeleton;
import net.minecraft.server.OEntitySlime;
import net.minecraft.server.OEntitySpider;
import net.minecraft.server.OEntitySquid;
import net.minecraft.server.OEntityVillager;
import net.minecraft.server.OEntityWolf;
import net.minecraft.server.OEntityZombie;
import net.minecraft.server.OEnumSkyBlock;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OTileEntityBrewingStand;
import net.minecraft.server.OTileEntityChest;
import net.minecraft.server.OTileEntityDispenser;
import net.minecraft.server.OTileEntityFurnace;
import net.minecraft.server.OTileEntityMobSpawner;
import net.minecraft.server.OTileEntityNote;
import net.minecraft.server.OTileEntityRecordPlayer;
import net.minecraft.server.OTileEntitySign;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldInfo;
import net.minecraft.server.OWorldServer;

/**
 * Wraps an OWorldServer object for world access. In OWorldServer there is no
 * _handler instance as we need the WorldContainer for instantiating a new World
 * wrapper. This is an exceptional thing
 * 
 * @author Chris
 * 
 */
public class CanaryDimension implements Dimension {

    private OWorldServer world;
    private CanaryChunkProviderServer chunkProvider;
    private CanaryWorld parent;
    private CanaryPlayerManager playerManager;
    private CanaryEntityTracker entityTracker;
    Type type;

    public CanaryDimension(OWorldServer world, CanaryWorld parent, Type type) {
        this.world = world; // We're not getting any other world types, right?
        chunkProvider = this.world.G.getCanaryChunkProvider(); // get the chunk provider wrapper
        this.parent = parent;
        world.setCanaryDimension(this);
        playerManager = world.getPlayerManager();
        this.type = type;
    }

    @Override
    public CanaryEntityTracker getEntityTracker() {
        return entityTracker;
    }

    @Override
    public void setEntityTracker(EntityTracker entityTracker) {
        this.entityTracker = (CanaryEntityTracker) entityTracker;
    }

    @Override
    public World getWorld() {
        return parent;
    }

    @Override
    public EntityItem dropItem(Vector3D position, Item item) {
        return dropItem((int)position.getX(), (int)position.getY(), (int)position.getZ(), item);
    }
    
    @Override
    public EntityItem dropItem(int x, int y, int z, int itemId, int amount, int damage) {
        double d1 = world.r.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d2 = world.r.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d3 = world.r.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;

        OEntityItem oei = new OEntityItem(world, x + d1, y + d2, z + d3,
                new OItemStack(itemId, amount, damage));

        oei.c = 10;
        world.b(oei);
        return oei.getItem();
    }

    @Override
    public EntityItem dropItem(int x, int y, int z, Item item) {
        double d1 = world.r.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d2 = world.r.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d3 = world.r.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;

        OEntityItem oei = new OEntityItem(world, x + d1, y + d2, z + d3, new OItemStack(item.getId(), item.getAmount(), item.getDamage()));

        oei.c = 10;
        world.b(oei);
        return oei.getItem();
    }

    @Override
    public ArrayList<EntityAnimal> getAnimalList() {
        ArrayList<EntityAnimal> animals = new ArrayList<EntityAnimal>();
        for(Entity entity : getEntityTracker().getTrackedEntities()) {
            if(entity instanceof EntityAnimal) {
                animals.add((EntityAnimal) entity);
            }
        }
        return animals;
    }

    @Override
    public ArrayList<EntityMob> getMobList() {
        ArrayList<EntityMob> mobs = new ArrayList<EntityMob>();
        for(Entity entity : getEntityTracker().getTrackedEntities()) {
            if(entity instanceof EntityMob) {
                mobs.add((EntityMob) entity);
            }
        }
        return mobs;
    }

    @Override
    public ArrayList<Player> getPlayerList() {
        return playerManager.getManagedPlayers();
    }

    @Override
    public Block getBlockAt(int x, int y, int z) {
        short id = (short) world.a(x, y, z);
        byte data = getDataAt(x, y, z);
        return new CanaryBlock(id, data, x, y, z, this);
    }
    
    @Override
    public byte getDataAt(Vector3D position) {
        return getDataAt((int)position.getX(), (int)position.getY(), (int)position.getZ());
    }
    
    @Override
    public Block getBlockAt(Vector3D position) {
        return getBlockAt((int)position.getX(), (int)position.getY(), (int)position.getZ());
    }

    @Override
    public byte getDataAt(int x, int y, int z) {
        return (byte) world.c(x, y, z);
    }

    @Override
    public void setBlock(Block block) {
        setBlockAt(block.getX(), block.getY(), block.getZ(), block.getType(), block.getData());
    }
    
    @Override
    public void setBlockAt(Vector3D vector, Block block) {
        setBlockAt((int)vector.getX(), (int)vector.getY(), (int)vector.getZ(), block.getType(), block.getData());
    }

    @Override
    public void setBlockAt(int x, int y, int z, short type) {
        setBlockAt(x, y, z, type, (byte) 0);

    }

    @Override
    public void setBlockAt(Vector3D position, short type) {
        setBlockAt((int)position.getX(), (int)position.getY(), (int)position.getZ(), (short)type);
    }

    @Override
    public void setBlockAt(Vector3D position, short type, byte data) {
        setBlockAt((int)position.getX(), (int)position.getY(), (int)position.getZ(), type, data);
    }

    @Override
    public void setBlockAt(int x, int y, int z, short type, byte data) {
        world.b(x, y, z, type, data);
    }

    @Override
    public void setDataAt(int x, int y, int z, byte data) {
        world.d(x, y, z, data);
    }

    @Override
    public void markBlockNeedsUpdate(int x, int y, int z) {
        world.j(x, y, z);
    }

    @Override
    public Player getClosestPlayer(double x, double y, double z, double distance) {
        OEntityPlayer user = world.a(x, y, z, distance);
        if((user != null) && user instanceof OEntityPlayerMP) {
            return (Player) user.getCanaryEntity();
        }
        return null;
    }

    @Override
    public Player getClosestPlayer(Entity entity, int distance) {
        OEntityPlayer user = world.a(((CanaryEntity)entity).getHandle(), distance);
        if((user != null) && user instanceof OEntityPlayerMP) {
            return (Player) user.getCanaryEntity();
        }
        return null;
    }

    @Override
    public ChunkProviderServer getChunkProvider() {
        return chunkProvider;
    }

    @Override
    public boolean isChunkLoaded(Block block) {
        chunkProvider.isChunkLoaded(block.getX(), block.getZ());
        return false;
    }

    @Override
    public boolean isChunkLoaded(int x, int y, int z) { //TODO: remove?
        return chunkProvider.isChunkLoaded(x, z);
    }

    @Override
    public boolean isChunkLoaded(int x, int z) { 
        return chunkProvider.isChunkLoaded(x, z);
    }

    @Override
    public int getHeight() {
        return 256;
    }

    @Override
    public int getYHeighestBlockAt(int x, int z) {
        return world.e(x, z);
    }

    @Override
    public void playNoteAt(int x, int y, int z, int instrument, byte notePitch) {
        world.e(x, y, y, instrument, notePitch);
    }

    @Override
    public void setTime(long time) {
        long margin = (time - getRawTime()) % 24000;

        // Java modulus is stupid.
        if (margin < 0) {
            margin += 24000;
        }
        long newTime = getRawTime() + margin;
        
        //Set time for every dimension to make sure it's all synced
        for (Dimension dim : parent.getDimensions()) {
            ((CanaryDimension) dim).getHandle().a(newTime);
        }
    }

    @Override
    public long getRelativeTime() {
        long time = (getRawTime() % 24000);

        // Java modulus is stupid.
        if (time < 0) {
            time += 24000;
        }
        return time;
    }

    @Override
    public long getRawTime() {
        return world.o();
    }

    @Override
    public int getLightLevelAt(int x, int y, int z) {
        return world.n(x, y, z);
    }

    @Override
    public void setLightLevelOnBlockMap(int x, int y, int z, int newLevel) {
        world.a(OEnumSkyBlock.b, x, y, z, newLevel);
    }

    @Override
    public void setLightLevelOnSkyMap(int x, int y, int z, int newLevel) {
        world.a(OEnumSkyBlock.a, x, y, z, newLevel);
    }
    
    @Override
    public Chunk loadChunk(int x, int z) {
        return chunkProvider.loadChunk(x, z);
    }

    @Override
    public Chunk loadChunk(Location location) {
        return chunkProvider.loadChunk((int)location.getX(), (int)location.getZ());
    }

    @Override
    public Chunk loadChunk(Vector3D vec3d) {
        return chunkProvider.loadChunk((int)vec3d.getX(), (int)vec3d.getZ());
    }
    
    @Override
    public Chunk getChunk(int x, int z) {
        if (isChunkLoaded(x, z)) {
            return chunkProvider.provideChunk(x, z);
        } 
        else {
            return null;
        }
    }

    public OWorld getHandle() {
        return world;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return parent.getName();
    }

    @Override
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void setPlayerManager(PlayerManager pm) {
        this.playerManager = (CanaryPlayerManager) pm;
    }

    @Override
    public void spawnParticle(Particle particle) {
        world.a(particle.type.getMcName(), particle.x, particle.y, particle.z, particle.velocityX, particle.velocityY, particle.velocityZ);
    }

    @Override
    public EntityMob createMob(MobType mobtype) {
        switch(mobtype) {
        case BLAZE:
            return new CanaryBlaze(new OEntityBlaze(getHandle()));
        case CREEPER:
            return new CanaryCreeper(new OEntityCreeper(getHandle()));
        case ENDERMAN:
            return new CanaryEnderman(new OEntityEnderman(getHandle()));
        case GHAST:
            return new CanaryGhast(new OEntityGhast(getHandle()));
        case GIANTZOMBIE:
            return new CanaryGiantZombie(new OEntityGiantZombie(getHandle()));
        case LAVASLIME:
            return new CanaryLavaSlime(new OEntityLavaSlime(getHandle()));
        case PIGZOMBIE:
            return new CanaryPigZombie(new OEntityPigZombie(getHandle()));
        case SILVERFISH:
            return new CanarySilverfish(new OEntitySilverfish(getHandle()));
        case SKELETON:
            return new CanarySkeleton(new OEntitySkeleton(getHandle()));
        case SLIME:
            return new CanarySlime(new OEntitySlime(getHandle()));
        case SPIDER:
            return new CanarySpider(new OEntitySpider(getHandle()));
        case ZOMBIE:
            return new CanaryZombie(new OEntityZombie(getHandle()));
        }
        return null;
    }

    @Override
    public boolean isBlockPowered(Block block) {
        return isBlockPowered(block.getX(), block.getY(), block.getZ());
    }

    @Override
    public boolean isBlockPowered(Vector3D position) {
        return isBlockPowered((int)position.getX(), (int)position.getY(), (int)position.getZ());
    }

    @Override
    public boolean isBlockPowered(int x, int y, int z) {
        return world.w(x, y, z);
    }

    @Override
    public boolean isBlockIndirectlyPowered(Block block) {
        return isBlockIndirectlyPowered(block.getX(), block.getY(), block.getZ());
    }

    @Override
    public boolean isBlockIndirectlyPowered(Vector3D position) {
        return isBlockIndirectlyPowered((int)position.getX(), (int)position.getY(), (int)position.getZ());
    }

    @Override
    public boolean isBlockIndirectlyPowered(int x, int y, int z) {
        return world.x(x, y, z);
    }

    @Override
    public void setThundering(boolean thundering) {
        //TODO: add thunder_change hook here?
        world.worldInfo.setThundering(thundering);

        // Thanks to Bukkit for figuring out these numbers
        if (thundering) {
            setThunderTime(world.r.nextInt(12000) + 3600);
        } else {
            setThunderTime(world.r.nextInt(168000) + 12000);
        }
        
    }

    @Override
    public void setThunderTime(int ticks) {
        world.worldInfo.setThunderTimeTicks(ticks);
    }

    @Override
    public void setRaining(boolean downfall) {
        //TODO: Add weather change hook
        world.worldInfo.setRaining(downfall);

        // Thanks to Bukkit for figuring out these numbers
        if (downfall) {
            setRainTime(world.r.nextInt(12000) + 3600);
        } else {
            setRainTime(world.r.nextInt(168000) + 12000);
        }
    }

    @Override
    public void setRainTime(int ticks) {
        world.worldInfo.setRainTimeTicks(ticks);
    }

    @Override
    public boolean isRaining() {
        return world.worldInfo.isRaining();
    }

    @Override
    public boolean isThundering() {
        return world.worldInfo.isThundering();
    }

    @Override
    public int getRainTicks() {
        return world.worldInfo.getRainTimeTicks();
    }

    @Override
    public int getThunderTicks() {
        return world.worldInfo.getThunderTimeTicks();
    }

    @Override
    public void makeLightningBolt(int x, int y, int z) {
        world.a(new OEntityLightningBolt(world, x, y, z));
    }
    
    @Override
    public void makeLightningBolt(Vector3D position) {
        world.a(new OEntityLightningBolt(world, (int)position.getX(), (int)position.getY(), (int)position.getZ()));
    }
    
    @Override
    public void makeExplosion(Entity exploder, double x, double y, double z, float power) {
        world.a(((CanaryEntity)exploder).getHandle(), x, y, z, power);
    }
    
    @Override
    public void makeExplosion(Entity exploder, Vector3D position, float power) {
        world.a(((CanaryEntity)exploder).getHandle(), position.getX(), position.getY(), position.getZ(), power);
    }
    
    @Override
    public long getWorldSeed() {
        return world.n();
    }

    @Override
    public void removePlayerFromWorld(Player player) {
        world.f((OEntity)((CanaryPlayer) player).getHandle());
        
    }

    @Override
    public void addPlayerToWorld(Player player) {
        world.b((OEntity)((CanaryPlayer)player).getHandle());
    }

    @Override
    public Location getSpawnLocation() {
     // More structure ftw
        OWorldInfo info = world.worldInfo;
        Location spawn = new Location(0,0,0);

        spawn.setX(info.getSpawnX() + 0.5D);
        spawn.setY(world.f(info.getSpawnX(), info.getSpawnZ()) + 1.5D);
        spawn.setZ(info.getSpawnZ() + 0.5D);
        spawn.setRotation(0.0F);
        spawn.setPitch(0.0F);
        spawn.setDimensionId(0);
        spawn.setWorldName(world.getCanaryDimension().getName());
        return spawn;
    }

    @Override
    public void setSpawnLocation(Location p) {
        world.worldInfo.setSpawn((int)p.getX(), (int)p.getY(), (int)p.getZ());
    }

    @Override
    public ComplexBlock getComplexBlock(Block block) {
        return getComplexBlockAt(block.getX(), block.getY(), block.getZ());
    }

    @Override
    public ComplexBlock getComplexBlockAt(int x, int y, int z) {
        ComplexBlock result = getOnlyComplexBlockAt(x, y, z);

        if (result != null) {
            if (result instanceof Chest) {
                Chest chest = (Chest) result;

                if(chest.hasAttachedChest()){
                    //return new CanaryDoubleChest(chest); TODO
                }
            }
        }

        return result;
    }

    @Override
    public ComplexBlock getOnlyComplexBlock(Block block) {
        return getOnlyComplexBlockAt(block.getX(), block.getY(), block.getZ());
    }

    @Override
    public ComplexBlock getOnlyComplexBlockAt(int x, int y, int z) {
        OTileEntity tileentity = world.b(x, y, z);

        if (tileentity != null) {
            if (tileentity instanceof OTileEntityChest) {
                return new CanaryChest((OTileEntityChest) tileentity);
            } else if (tileentity instanceof OTileEntitySign) {
                return new CanarySign((OTileEntitySign) tileentity);
            } else if (tileentity instanceof OTileEntityFurnace) {
                return new CanaryFurnace((OTileEntityFurnace) tileentity);
            } else if (tileentity instanceof OTileEntityMobSpawner) {
                return new CanaryMobSpawner((OTileEntityMobSpawner) tileentity);
            } else if (tileentity instanceof OTileEntityDispenser) {
                return new CanaryDispenser((OTileEntityDispenser) tileentity);
            } else if (tileentity instanceof OTileEntityNote) {
                return new CanaryNoteBlock((OTileEntityNote) tileentity);
            } else if (tileentity instanceof OTileEntityBrewingStand) {
                return new CanaryBrewingStand((OTileEntityBrewingStand) tileentity);
            } else if (tileentity instanceof OTileEntityRecordPlayer) {
                return new CanaryJukebox((OTileEntityRecordPlayer) tileentity);
            }
        }
        return null;
    }

    @Override
    public EntityAnimal createAnimal(AnimalType animalType) {
        switch(animalType) {
            case CHICKEN:
                return new CanaryChicken(new OEntityChicken(getHandle()));
            case COW:
                return new CanaryCow(new OEntityCow(getHandle()));
            case MUSHROOMCOW:
                return new CanaryMushroomCow(new OEntityMooshroom(getHandle()));
            case OCELOT:
                return new CanaryOcelot(new OEntityOcelot(getHandle()));
            case PIG:
                return new CanaryPig(new OEntityPig(getHandle()));
            case SHEEP:
                return new CanarySheep(new OEntitySheep(getHandle()));
            case SQUID:
                return new CanarySquid(new OEntitySquid(getHandle()));
            case VILLAGER:
                return new CanaryVillager(new OEntityVillager(getHandle()));
            case WOLF:
                return new CanaryWolf(new OEntityWolf(getHandle()));
        }
        return null;
    }

    @Override
    public Item createItem(ItemType itemType) {
        return new CanaryItem(new OItemStack(itemType.getId(), 1, 0));
    }

    @Override
    public Item createItem(ItemType itemType, int amount, int data) {
        return new CanaryItem(new OItemStack(itemType.getId(), amount, data));
    }

    @Override
    public Item createItem(int itemId, int amount, int data) {
        
        return new CanaryItem(new OItemStack(itemId, amount, data));
    }
}
