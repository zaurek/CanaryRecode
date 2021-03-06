package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.server.ONBTBase;

public class ONBTTagFloat extends ONBTBase {

    public float a;

    public ONBTTagFloat(String var1) {
        super(var1);
    }

    public ONBTTagFloat(String var1, float var2) {
        super(var1);
        this.a = var2;
    }

    @Override
    void a(DataOutput var1) throws IOException {
        var1.writeFloat(this.a);
    }

    @Override
    void a(DataInput var1) throws IOException {
        this.a = var1.readFloat();
    }

    @Override
    public byte a() {
        return (byte) 5;
    }

    @Override
    public String toString() {
        return "" + this.a;
    }

    @Override
    public ONBTBase b() {
        return new ONBTTagFloat(this.c(), this.a);
    }

    @Override
    public boolean equals(Object var1) {
        if (super.equals(var1)) {
            ONBTTagFloat var2 = (ONBTTagFloat) var1;
            return this.a == var2.a;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ Float.floatToIntBits(this.a);
    }
}
