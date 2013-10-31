package deity.skillsystem.network;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

public abstract class BasePacket
{

	private static BiMap<Integer, Class<? extends BasePacket>> packets;

	static
	{
		ImmutableBiMap.Builder<Integer, Class<? extends BasePacket>> builder = ImmutableBiMap.builder();

		packets = builder.build();
	}

	public static BasePacket construct(int packetID) throws ProtocolException, ReflectiveOperationException
	{
		Class<? extends BasePacket> clazz = packets.get(Integer.valueOf(packetID));

		if (clazz != null) 
			return clazz.newInstance();

		throw new ProtocolException("Unknown Packet ID");
	}

	public static class ProtocolException extends Exception
	{
		private static final long serialVersionUID = 2123720146207233334L;

		public ProtocolException()
		{
		}

		public ProtocolException(String message, Throwable cause)
		{
			super(message, cause);
		}

		public ProtocolException(String message)
		{
			super(message);
		}

		public ProtocolException(Throwable cause)
		{
			super(cause);
		}
	}

	public final int getPacketID()
	{
		if (packets.inverse().containsKey(getClass())) 
			return packets.inverse().get(getClass()).intValue();

		throw new RuntimeException("Packet " + getClass().getSimpleName() + " is not registered.");
	}

	public final net.minecraft.network.packet.Packet makePacket()
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeByte(getPacketID());
		write(out);

		return PacketDispatcher.getPacket("TEMPORARY", out.toByteArray());
	}

	public abstract void write(ByteArrayDataOutput out);

	public abstract void read(ByteArrayDataInput in) throws ProtocolException;

	public abstract void execute(EntityPlayer player, Side side) throws ProtocolException;
}