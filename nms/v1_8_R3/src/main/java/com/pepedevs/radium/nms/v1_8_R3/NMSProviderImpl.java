package com.pepedevs.radium.nms.v1_8_R3;

import com.mojang.authlib.GameProfile;
import com.pepedevs.radium.nms.EnumGameMode;
import com.pepedevs.radium.nms.NMSPlayer;
import com.pepedevs.radium.nms.NMSProvider;
import com.pepedevs.radium.nms.objects.WrappedDataWatcher;
import com.pepedevs.radium.nms.objects.WrappedPacketDataSerializer;
import com.pepedevs.radium.nms.objects.WrappedPlayerInfoData;
import com.pepedevs.radium.nms.v1_8_R3.objects.WrappedDataWatcherImpl;
import com.pepedevs.radium.nms.v1_8_R3.objects.WrappedPacketDataSerializerImpl;
import com.pepedevs.radium.nms.v1_8_R3.objects.WrappedPlayerInfoDataImpl;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.kyori.adventure.text.Component;
import net.minecraft.server.v1_8_R3.EnumChatFormat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NMSProviderImpl implements NMSProvider {

    public static final NMSProviderImpl INSTANCE = new NMSProviderImpl();

    private NMSProviderImpl(){}

    @Override
    public String craftChatMessageFromComponent(Object component) {
        IChatBaseComponent chatBaseComponent = (IChatBaseComponent) component;
        return CraftChatMessage.fromComponent(chatBaseComponent);
    }

    @Override
    public Object[] craftChatMessageFromString(String message) {
        return CraftChatMessage.fromString(message);
    }

    @Override
    public NMSPlayer getPlayer(Player player) {
        return new com.pepedevs.radium.nms.v1_8_R3.NMSPlayer(player);
    }

    @Override
    public WrappedPacketDataSerializer getDataSerializer() {
        return this.getDataSerializer(Unpooled.buffer());
    }

    @Override
    public WrappedPacketDataSerializer getDataSerializer(ByteBuf byteBuf) {
        return new WrappedPacketDataSerializerImpl(byteBuf);
    }

    @Override
    public WrappedDataWatcher getDataWatcher() {
        return new WrappedDataWatcherImpl();
    }

    @Override
    public WrappedDataWatcher.WrappedWatchableObject getWatchableObject(Object watchableObject) {
        return new WrappedDataWatcherImpl.WrappedWatchableObjectImpl(watchableObject);
    }

    @Override
    public WrappedDataWatcher.WrappedWatchableObject getWatchableObject(int i, int j, Object o) {
        return new WrappedDataWatcherImpl.WrappedWatchableObjectImpl(i, j, o);
    }

    @Override
    public WrappedPlayerInfoData getPlayerInfo(GameProfile gameProfile, int latency, EnumGameMode gamemode, String name) {
        return new WrappedPlayerInfoDataImpl(gameProfile, latency, gamemode, name);
    }

    @Override
    public WrappedPlayerInfoData getPlayerInfo(GameProfile gameProfile, int latency, EnumGameMode gamemode, Component name) {
        return new WrappedPlayerInfoDataImpl(gameProfile, latency, gamemode, name);
    }

    @Override
    public WrappedPlayerInfoData getPlayerInfo(GameProfile gameProfile, int latency, EnumGameMode gamemode, Object name) {
        return new WrappedPlayerInfoDataImpl(gameProfile, latency, gamemode, name);
    }

    @Override
    public void craftEventFactoryHandleInventoryClose(Player player) {
        CraftEventFactory.handleInventoryCloseEvent(((CraftPlayer) player).getHandle());
    }

    @Override
    public Enum<?> getEnumChatFormat(ChatColor color) {
        if (color == ChatColor.MAGIC)
            return EnumChatFormat.OBFUSCATED;

        return EnumChatFormat.valueOf(color.name());
    }

    @Override
    public ChatColor getEnumChatFormat(Enum<?> enumChatFormat) {
        if (enumChatFormat == EnumChatFormat.OBFUSCATED)
            return ChatColor.MAGIC;

        return ChatColor.valueOf(enumChatFormat.name());
    }

    @Override
    public Object craftItemStackAsNmsCopy(ItemStack itemStack) {
        return CraftItemStack.asNMSCopy(itemStack);
    }

    @Override
    public Object craftItemStackAsCraftCopy(ItemStack itemStack) {
        return CraftItemStack.asCraftCopy(itemStack);
    }

    @Override
    public ItemStack craftItemStackAsBukkitCopy(Object nmsItemStack) {
        return CraftItemStack.asBukkitCopy((net.minecraft.server.v1_8_R3.ItemStack) nmsItemStack);
    }

}