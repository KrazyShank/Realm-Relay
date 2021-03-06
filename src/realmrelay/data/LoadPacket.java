package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class LoadPacket extends Packet {
	
	public static final byte ID = 17;
	
	public int charId;
	public boolean isFromArena;

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.charId = in.readInt();
		this.isFromArena = in.readBoolean();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.charId);
		out.writeBoolean(this.isFromArena);
	}

}
