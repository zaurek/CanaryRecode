package net.minecraft.server;

import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBlock;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OChunkProviderEnd;
import net.minecraft.server.OIChunkProvider;
import net.minecraft.server.OWorldChunkManagerHell;
import net.minecraft.server.OWorldProvider;

public class OWorldProviderEnd extends OWorldProvider {

    public OWorldProviderEnd() {
        super();
    }

    @Override
    public void a() {
        this.c = new OWorldChunkManagerHell(OBiomeGenBase.k, 0.5F, 0.0F);
        this.g = 1;
        this.e = true;
    }

    @Override
    public OIChunkProvider b() {
        return new OChunkProviderEnd(this.a, this.a.n());
    }

    @Override
    public float a(long var1, float var3) {
        return 0.0F;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean a(int var1, int var2) {
        int var3 = this.a.b(var1, var2);
        return var3 == 0 ? false : OBlock.m[var3].cd.c();
    }

    @Override
    public OChunkCoordinates e() {
        return new OChunkCoordinates(100, 50, 0);
    }

    @Override
    public int f() {
        return 50;
    }
}
