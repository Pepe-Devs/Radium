package com.pepedevs.corelib.nms.v1_12_R1.packets;

import com.pepedevs.corelib.nms.packets.WrappedPacketDataSerializer;
import com.pepedevs.corelib.nms.packets.WrappedPacketPlayOutEntityEquipment;
import com.pepedevs.corelib.nms.v1_12_R1.NMSImpl;
import net.minecraft.server.v1_12_R1.PacketDataSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class WrappedPacketPlayOutEntityEquipmentImpl extends PacketPlayOutEntityEquipment implements WrappedPacketPlayOutEntityEquipment {

    public WrappedPacketPlayOutEntityEquipmentImpl(int entityID, int slot, ItemStack itemStack) {
        WrappedPacketDataSerializer serializer = NMSImpl.INSTANCE.getDataSerializer();
        serializer.serializeIntToByte(entityID).serializeShort((short) slot).serializeItemStack(itemStack);
        try {
            this.a((PacketDataSerializer) serializer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WrappedPacketPlayOutEntityEquipmentImpl(WrappedPacketDataSerializer serializer) {
        try {
            this.a((PacketDataSerializer) serializer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
