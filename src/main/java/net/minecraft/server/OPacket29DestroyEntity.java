package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket29DestroyEntity extends OPacket {

    public int a;

    public OPacket29DestroyEntity() {
        super();
    }

    public OPacket29DestroyEntity(int var1) {
        super();
        this.a = var1;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 4;
    }
}
