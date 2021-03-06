package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class PlaySoundPacket extends Packet {
	
	public static final byte ID = 60;
	
	public int ownerId;
	public int soundId;

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.ownerId = in.readInt();
		this.soundId = in.readUnsignedByte();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.ownerId);
		out.writeByte(this.soundId);
	}

}
