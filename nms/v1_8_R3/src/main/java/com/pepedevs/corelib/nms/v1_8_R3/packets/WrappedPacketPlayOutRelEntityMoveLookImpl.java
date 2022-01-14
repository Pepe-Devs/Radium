package com.pepedevs.corelib.nms.v1_8_R3.packets;

import com.pepedevs.corelib.nms.objects.WrappedPacketDataSerializer;
import com.pepedevs.corelib.nms.packets.WrappedPacketPlayOutRelEntityMoveLook;
import com.pepedevs.corelib.nms.v1_8_R3.NMSProviderImpl;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;
import org.bukkit.Location;

import java.io.IOException;

public class WrappedPacketPlayOutRelEntityMoveLookImpl implements WrappedPacketPlayOutRelEntityMoveLook {

    private int entityId;
    private int deltaX;
    private int deltaY;
    private int deltaZ;
    private float yaw;
    private float pitch;
    private boolean onGround;

    public WrappedPacketPlayOutRelEntityMoveLookImpl(int entityId, int deltaX, int deltaY, int deltaZ, float yaw, float pitch, boolean onGround) {
        this.entityId = entityId;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    public WrappedPacketPlayOutRelEntityMoveLookImpl(int entityId, Location oldLocation, Location newLocation, boolean onGround) {
        this.entityId = entityId;
        this.deltaX = MathHelper.floor(oldLocation.getX() * 32.0D) - MathHelper.floor(newLocation.getX() * 32.0D);
        this.deltaY = MathHelper.floor(oldLocation.getY() * 32.0D) - MathHelper.floor(newLocation.getY() * 32.0D);
        this.deltaZ = MathHelper.floor(oldLocation.getZ() * 32.0D) - MathHelper.floor(newLocation.getZ() * 32.0D);
        this.yaw = newLocation.getYaw();
        this.pitch = newLocation.getPitch();
        this.onGround = onGround;
    }

    public WrappedPacketPlayOutRelEntityMoveLookImpl(WrappedPacketDataSerializer serializer) {
        PacketDataSerializer dataSerializer = (PacketDataSerializer) serializer;
        this.entityId = dataSerializer.e();
        this.deltaX = dataSerializer.readByte();
        this.deltaY = dataSerializer.readByte();
        this.deltaZ = dataSerializer.readByte();
        this.yaw = dataSerializer.readByte() * 360.0F / 256.0F;
        this.pitch = dataSerializer.readByte() * 360.0F / 256.0F;
        this.onGround = dataSerializer.readBoolean();
    }

    @Override
    public int getEntityId() {
        return entityId;
    }

    @Override
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public int getDeltaX() {
        return deltaX;
    }

    @Override
    public void setDeltaX(int xDiff) {
        this.deltaX = xDiff;
    }

    @Override
    public int getDeltaY() {
        return deltaY;
    }

    @Override
    public void setDeltaY(int yDiff) {
        this.deltaY = yDiff;
    }

    @Override
    public int getDeltaZ() {
        return deltaZ;
    }

    @Override
    public void setDeltaZ(int zDiff) {
        this.deltaZ = zDiff;
    }

    @Override
    public float getYaw() {
        return yaw;
    }

    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    @Override
    public float getPitch() {
        return pitch;
    }

    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    @Override
    public WrappedPacketDataSerializer buildData() {
        WrappedPacketDataSerializer serializer = NMSProviderImpl.INSTANCE.getDataSerializer();
        serializer.serializeVarInt(this.entityId)
                .serializeByte(this.deltaX)
                .serializeByte(this.deltaY)
                .serializeByte(this.deltaZ)
                .serializeByte(MathHelper.d(this.yaw * 256.0F / 360.0F))
                .serializeByte(MathHelper.d(this.pitch * 256.0F / 360.0F))
                .serializeBoolean(this.onGround);
        return serializer;
    }

    @Override
    public Object buildPacket() {
        PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook packet = new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook();
        try {
            packet.a((PacketDataSerializer) buildData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packet;
    }

}
