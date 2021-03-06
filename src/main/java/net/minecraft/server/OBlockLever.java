package net.minecraft.server;

import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockLever extends OBlock {

    protected OBlockLever(int var1, int var2) {
        super(var1, var2, OMaterial.p);
    }

    @Override
    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        return null;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public int c() {
        return 12;
    }

    @Override
    public boolean b(OWorld var1, int var2, int var3, int var4, int var5) {
        return var5 == 1 && var1.e(var2, var3 - 1, var4) ? true : (var5 == 2 && var1.e(var2, var3, var4 + 1) ? true : (var5 == 3 && var1.e(var2, var3, var4 - 1) ? true : (var5 == 4 && var1.e(var2 + 1, var3, var4) ? true : var5 == 5 && var1.e(var2 - 1, var3, var4))));
    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return var1.e(var2 - 1, var3, var4) ? true : (var1.e(var2 + 1, var3, var4) ? true : (var1.e(var2, var3, var4 - 1) ? true : (var1.e(var2, var3, var4 + 1) ? true : var1.e(var2, var3 - 1, var4))));
    }

    @Override
    public void e(OWorld var1, int var2, int var3, int var4, int var5) {
        int var6 = var1.c(var2, var3, var4);
        int var7 = var6 & 8;
        var6 &= 7;
        var6 = -1;
        if (var5 == 1 && var1.e(var2, var3 - 1, var4)) {
            var6 = 5 + var1.r.nextInt(2);
        }

        if (var5 == 2 && var1.e(var2, var3, var4 + 1)) {
            var6 = 4;
        }

        if (var5 == 3 && var1.e(var2, var3, var4 - 1)) {
            var6 = 3;
        }

        if (var5 == 4 && var1.e(var2 + 1, var3, var4)) {
            var6 = 2;
        }

        if (var5 == 5 && var1.e(var2 - 1, var3, var4)) {
            var6 = 1;
        }

        if (var6 == -1) {
            this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
            var1.e(var2, var3, var4, 0);
        } else {
            var1.c(var2, var3, var4, var6 + var7);
        }
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        if (this.g(var1, var2, var3, var4)) {
            int var6 = var1.c(var2, var3, var4) & 7;
            boolean var7 = false;
            if (!var1.e(var2 - 1, var3, var4) && var6 == 1) {
                var7 = true;
            }

            if (!var1.e(var2 + 1, var3, var4) && var6 == 2) {
                var7 = true;
            }

            if (!var1.e(var2, var3, var4 - 1) && var6 == 3) {
                var7 = true;
            }

            if (!var1.e(var2, var3, var4 + 1) && var6 == 4) {
                var7 = true;
            }

            if (!var1.e(var2, var3 - 1, var4) && var6 == 5) {
                var7 = true;
            }

            if (!var1.e(var2, var3 - 1, var4) && var6 == 6) {
                var7 = true;
            }

            if (var7) {
                this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
                var1.e(var2, var3, var4, 0);
            }
        }

    }

    private boolean g(OWorld var1, int var2, int var3, int var4) {
        if (!this.c(var1, var2, var3, var4)) {
            this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
            var1.e(var2, var3, var4, 0);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4) & 7;
        float var6 = 0.1875F;
        if (var5 == 1) {
            this.a(0.0F, 0.2F, 0.5F - var6, var6 * 2.0F, 0.8F, 0.5F + var6);
        } else if (var5 == 2) {
            this.a(1.0F - var6 * 2.0F, 0.2F, 0.5F - var6, 1.0F, 0.8F, 0.5F + var6);
        } else if (var5 == 3) {
            this.a(0.5F - var6, 0.2F, 0.0F, 0.5F + var6, 0.8F, var6 * 2.0F);
        } else if (var5 == 4) {
            this.a(0.5F - var6, 0.2F, 1.0F - var6 * 2.0F, 0.5F + var6, 0.8F, 1.0F);
        } else {
            var6 = 0.25F;
            this.a(0.5F - var6, 0.0F, 0.5F - var6, 0.5F + var6, 0.6F, 0.5F + var6);
        }

    }

    @Override
    public void b(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        this.a(var1, var2, var3, var4, var5);
    }

    @Override
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (var1.F) {
            return true;
        } else {
            int var6 = var1.c(var2, var3, var4);
            int var7 = var6 & 7;
            int var8 = 8 - (var6 & 8);
            var1.c(var2, var3, var4, var7 + var8);
            var1.b(var2, var3, var4, var2, var3, var4);
            var1.a(var2 + 0.5D, var3 + 0.5D, var4 + 0.5D, "random.click", 0.3F, var8 > 0 ? 0.6F : 0.5F);
            var1.h(var2, var3, var4, this.bO);
            if (var7 == 1) {
                var1.h(var2 - 1, var3, var4, this.bO);
            } else if (var7 == 2) {
                var1.h(var2 + 1, var3, var4, this.bO);
            } else if (var7 == 3) {
                var1.h(var2, var3, var4 - 1, this.bO);
            } else if (var7 == 4) {
                var1.h(var2, var3, var4 + 1, this.bO);
            } else {
                var1.h(var2, var3 - 1, var4, this.bO);
            }

            return true;
        }
    }

    @Override
    public void d(OWorld var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4);
        if ((var5 & 8) > 0) {
            var1.h(var2, var3, var4, this.bO);
            int var6 = var5 & 7;
            if (var6 == 1) {
                var1.h(var2 - 1, var3, var4, this.bO);
            } else if (var6 == 2) {
                var1.h(var2 + 1, var3, var4, this.bO);
            } else if (var6 == 3) {
                var1.h(var2, var3, var4 - 1, this.bO);
            } else if (var6 == 4) {
                var1.h(var2, var3, var4 + 1, this.bO);
            } else {
                var1.h(var2, var3 - 1, var4, this.bO);
            }
        }

        super.d(var1, var2, var3, var4);
    }

    @Override
    public boolean a(OIBlockAccess var1, int var2, int var3, int var4, int var5) {
        return (var1.c(var2, var3, var4) & 8) > 0;
    }

    @Override
    public boolean d(OWorld var1, int var2, int var3, int var4, int var5) {
        int var6 = var1.c(var2, var3, var4);
        if ((var6 & 8) == 0) {
            return false;
        } else {
            int var7 = var6 & 7;
            return var7 == 6 && var5 == 1 ? true : (var7 == 5 && var5 == 1 ? true : (var7 == 4 && var5 == 2 ? true : (var7 == 3 && var5 == 3 ? true : (var7 == 2 && var5 == 4 ? true : var7 == 1 && var5 == 5))));
        }
    }

    @Override
    public boolean e() {
        return true;
    }
}
