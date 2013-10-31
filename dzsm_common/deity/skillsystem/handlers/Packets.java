package deity.skillsystem.handlers;

import java.util.logging.Logger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import deity.skillsystem.network.BasePacket;
import deity.skillsystem.network.BasePacket.ProtocolException;

public class Packets implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		try
		{
			EntityPlayer ep = (EntityPlayer) player;

			ByteArrayDataInput in = ByteStreams.newDataInput(packet.data);
			int packetId = in.readUnsignedByte();

			BasePacket pkt = BasePacket.construct(packetId);
			pkt.read(in);
			pkt.execute(ep, ep.worldObj.isRemote ? Side.CLIENT : Side.SERVER);

		} catch (ProtocolException e)
		{
			if (player instanceof EntityPlayerMP)
			{
				((EntityPlayerMP) player).playerNetServerHandler.kickPlayerFromServer("Protocol Exception!");
				Logger.getLogger("Skill Mod").warning("Player " + ((EntityPlayer) player).username + " caused a Protocol Exception!");
			}

		} catch (ReflectiveOperationException e)
		{
			throw new RuntimeException("Unexpected Reflection exception during Packet construction!", e);

		}
	}
}