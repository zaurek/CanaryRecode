package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket101CloseWindow extends OPacket {

    public int a;

    public OPacket101CloseWindow() {
        super();
    }

    public OPacket101CloseWindow(int var1) {
        super();
        this.a = var1;
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readByte();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeByte(this.a);
    }

    @Override
    public int a() {
        return 1;
    }
}
