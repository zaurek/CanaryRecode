package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OEntityXPOrb;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket26EntityExpOrb extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public OPacket26EntityExpOrb() {
        super();
    }

    public OPacket26EntityExpOrb(OEntityXPOrb var1) {
        super();
        this.a = var1.bd;
        this.b = OMathHelper.b(var1.bm * 32.0D);
        this.c = OMathHelper.b(var1.bn * 32.0D);
        this.d = OMathHelper.b(var1.bo * 32.0D);
        this.e = var1.y_();
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readInt();
        this.c = var1.readInt();
        this.d = var1.readInt();
        this.e = var1.readShort();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeInt(this.b);
        var1.writeInt(this.c);
        var1.writeInt(this.d);
        var1.writeShort(this.e);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 18;
    }
}
