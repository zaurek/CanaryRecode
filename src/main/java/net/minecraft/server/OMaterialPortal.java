package net.minecraft.server;

import net.minecraft.server.OMapColor;
import net.minecraft.server.OMaterial;

public class OMaterialPortal extends OMaterial {

    public OMaterialPortal(OMapColor var1) {
        super(var1);
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
    public boolean c() {
        return false;
    }
}
