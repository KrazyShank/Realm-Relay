package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class TradeAcceptedPacket extends Packet {
	
	public static final byte ID = 86;
	
	public boolean[] myOffer = new boolean[0];
	public boolean[] yourOffer = new boolean[0];

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.myOffer = new boolean[in.readShort()];
		for (int i = 0; i < this.myOffer.length; i++) {
			this.myOffer[i] = in.readBoolean();
		}
		this.yourOffer = new boolean[in.readShort()];
		for (int i = 0; i < this.yourOffer.length; i++) {
			this.yourOffer[i] = in.readBoolean();
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(this.myOffer.length);
		for (boolean b: this.myOffer) {
			out.writeBoolean(b);
		}
		out.writeShort(this.yourOffer.length);
		for (boolean b: this.yourOffer) {
			out.writeBoolean(b);
		}
	}

}
