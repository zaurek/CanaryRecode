package net.minecraft.server;

import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntitySnowball;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OItemSnowball extends OItem {

    public OItemSnowball(int var1) {
        super(var1);
        this.bQ = 16;
    }

    @Override
    public OItemStack a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        if (!var3.L.d) {
            --var1.a;
        }

        var2.a(var3, "random.bow", 0.5F, 0.4F / (c.nextFloat() * 0.4F + 0.8F));
        if (!var2.F) {
            var2.b((new OEntitySnowball(var2, var3)));
        }

        return var1;
    }
}
