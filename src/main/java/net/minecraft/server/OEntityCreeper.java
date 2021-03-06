package net.minecraft.server;

import net.canarymod.api.entity.CanaryCreeper;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIAttackOnCollide;
import net.minecraft.server.OEntityAIAvoidEntity;
import net.minecraft.server.OEntityAICreeperSwell;
import net.minecraft.server.OEntityAIHurtByTarget;
import net.minecraft.server.OEntityAILookIdle;
import net.minecraft.server.OEntityAINearestAttackableTarget;
import net.minecraft.server.OEntityAISwimming;
import net.minecraft.server.OEntityAIWander;
import net.minecraft.server.OEntityAIWatchClosest;
import net.minecraft.server.OEntityLightningBolt;
import net.minecraft.server.OEntityMob;
import net.minecraft.server.OEntityOcelot;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntitySkeleton;
import net.minecraft.server.OItem;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public class OEntityCreeper extends OEntityMob {

    int a;
    int b;
    private CanaryCreeper canaryCreeper;

    public OEntityCreeper(OWorld var1) {
        super(var1);
        this.ae = "/mob/creeper.png";
        this.aL.a(1, new OEntityAISwimming(this));
        this.aL.a(2, new OEntityAICreeperSwell(this));
        this.aL.a(3, new OEntityAIAvoidEntity(this, OEntityOcelot.class, 6.0F, 0.25F, 0.3F));
        this.aL.a(4, new OEntityAIAttackOnCollide(this, 0.25F, false));
        this.aL.a(5, new OEntityAIWander(this, 0.2F));
        this.aL.a(6, new OEntityAIWatchClosest(this, OEntityPlayer.class, 8.0F));
        this.aL.a(6, new OEntityAILookIdle(this));
        this.aM.a(1, new OEntityAINearestAttackableTarget(this, OEntityPlayer.class, 16.0F, 0, true));
        this.aM.a(2, new OEntityAIHurtByTarget(this, false));
        canaryCreeper = new CanaryCreeper(this);
    }
    
    /**
     * CanaryMod Get CanaryCreeper handler
     * @return
     */
    public CanaryCreeper getCanaryCreeper() {
        return canaryCreeper;
    }

    @Override
    public boolean c_() {
        return true;
    }

    @Override
    public int d() {
        return 20;
    }

    @Override
    protected void b() {
        super.b();
        this.bY.a(16, Byte.valueOf((byte) -1));
        this.bY.a(17, Byte.valueOf((byte) 0));
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        if (this.bY.a(17) == 1) {
            var1.a("powered", true);
        }

    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.bY.b(17, Byte.valueOf((byte) (var1.o("powered") ? 1 : 0)));
    }

    @Override
    public void F_() {
        if (this.aE()) {
            this.b = this.a;
            int var1 = this.A();
            if (var1 > 0 && this.a == 0) {
                this.bi.a(this, "random.fuse", 1.0F, 0.5F);
            }

            this.a += var1;
            if (this.a < 0) {
                this.a = 0;
            }

            if (this.a >= 30) {
                this.a = 30;
                if (!this.bi.F) {
                    if (this.x()) {
                        this.bi.a(this, this.bm, this.bn, this.bo, 6.0F);
                    } else {
                        this.bi.a(this, this.bm, this.bn, this.bo, 3.0F);
                    }

                    this.X();
                }
            }
        }

        super.F_();
    }

    @Override
    protected String j() {
        return "mob.creeper";
    }

    @Override
    protected String k() {
        return "mob.creeperdeath";
    }

    @Override
    public void a(ODamageSource var1) {
        super.a(var1);
        if (var1.a() instanceof OEntitySkeleton) {
            this.b(OItem.bE.bP + this.bS.nextInt(10), 1);
        }

    }

    @Override
    public boolean a(OEntity var1) {
        return true;
    }

    /**
     * Is powered?
     * Returns true is creeper is powered (datawatcher byte is 1)
     * @return
     */
    public boolean x() {
        return this.bY.a(17) == 1;
    }
    
    /**
     * CanaryMod added method to manually set this creeper charged or make it de-charged
     * @param charged True to charge, false to decharge (set uncharged)
     */
    public void setCharged(boolean charged) {
        this.bY.b(1, charged);
    }

    @Override
    protected int f() {
        return OItem.L.bP;
    }

    public int A() {
        return this.bY.a(16);
    }

    public void c(int var1) {
        this.bY.b(16, Byte.valueOf((byte) var1));
    }

    @Override
    public void a(OEntityLightningBolt var1) {
        super.a(var1);
        this.bY.b(17, Byte.valueOf((byte) 1));
    }
}
