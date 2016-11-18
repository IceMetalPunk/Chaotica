package com.icemetalpunk.chaotica.handlers;

import com.icemetalpunk.chaotica.tileentities.TileEntityChaoticCondenser;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ChaoticaMessage implements IMessage {

	public int val = 0;
	public double x, y, z;
	public MessageTypes messageType;

	public static enum MessageTypes {
		CONDENSER_LEVEL
	};

	public ChaoticaMessage() {
	};

	public ChaoticaMessage(MessageTypes type, double x, double y, double z, int state) {
		this.val = state;
		this.x = x;
		this.y = y;
		this.z = z;
		this.messageType = type;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.messageType = MessageTypes.values()[buf.readByte()];
		this.x = buf.readDouble();
		this.y = buf.readDouble();
		this.z = buf.readDouble();
		this.val = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(this.messageType.ordinal());
		buf.writeDouble(this.x);
		buf.writeDouble(this.y);
		buf.writeDouble(this.z);
		buf.writeInt(this.val);
	}

	public static class ChaoticaMessageHandler implements IMessageHandler<ChaoticaMessage, IMessage> {

		@Override
		public IMessage onMessage(ChaoticaMessage message, MessageContext ctx) {
			if (message.messageType == MessageTypes.CONDENSER_LEVEL) {
				EntityPlayer player = Minecraft.getMinecraft().thePlayer;
				World world = player.worldObj;
				TileEntityChaoticCondenser tileEntity = (TileEntityChaoticCondenser) world.getTileEntity(new BlockPos(message.x, message.y, message.z));
				if (tileEntity != null) {
					tileEntity.setFluidAmount(message.val);
				}
			}
			return null;
		}

	}

}
