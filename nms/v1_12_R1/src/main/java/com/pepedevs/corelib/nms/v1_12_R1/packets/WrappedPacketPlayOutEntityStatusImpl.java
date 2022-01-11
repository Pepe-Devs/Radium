package com.pepedevs.corelib.nms.v1_12_R1.packets;

import com.pepedevs.corelib.nms.EntityStatus;
import com.pepedevs.corelib.nms.objects.WrappedPacketDataSerializer;
import com.pepedevs.corelib.nms.packets.WrappedPacketPlayOutEntityStatus;
import com.pepedevs.corelib.nms.v1_12_R1.NMSProviderImpl;
import net.minecraft.server.v1_12_R1.PacketDataSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityStatus;

import java.io.IOException;

public class WrappedPacketPlayOutEntityStatusImpl extends PacketPlayOutEntityStatus implements WrappedPacketPlayOutEntityStatus {

    public WrappedPacketPlayOutEntityStatusImpl(int entityID, EntityStatus status) {
        this(entityID, status.STATUS_NUMBER);
    }

    public WrappedPacketPlayOutEntityStatusImpl(int entityID, byte status) {
        WrappedPacketDataSerializer serializer = NMSProviderImpl.INSTANCE.getDataSerializer().serializeInt(entityID).serializeByte(status);
        try {
            this.a((PacketDataSerializer) serializer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WrappedPacketPlayOutEntityStatusImpl(WrappedPacketDataSerializer serializer) {
        try {
            this.a((PacketDataSerializer) serializer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}