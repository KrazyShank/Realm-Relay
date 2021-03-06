package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class MapInfoPacket extends Packet {
	
	public static final byte ID = 76;
	
	public int width;
	public int height;
	public String name;
	public String obf0;
	public int obf1;
	public int fp;
	public int background;
	public boolean allowPlayerTeleport;
	public boolean showDisplays;
	public String[] clientXML = new String[0];
	public String[] extraXML = new String[0];

	@Override
	public byte id() {
		return ID;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.width = in.readInt();
		this.height = in.readInt();
		this.name = in.readUTF();
		this.obf0 = in.readUTF();
		this.fp = in.readInt(); // TODO: fp is supposed to be unsigned
		this.background = in.readInt();
		this.obf1 = in.readInt();
		this.allowPlayerTeleport = in.readBoolean();
		this.showDisplays = in.readBoolean();
		this.clientXML = new String[in.readShort()];
		for (int i = 0; i < this.clientXML.length; i++) {
			byte[] utf = new byte[in.readInt()];
			in.readFully(utf);
			this.clientXML[i] = new String(utf, "UTF-8");
		}
		this.extraXML = new String[in.readShort()];
		for (int i = 0; i < this.extraXML.length; i++) {
			byte[] utf = new byte[in.readInt()];
			in.readFully(utf);
			this.extraXML[i] = new String(utf, "UTF-8");
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.width);
		out.writeInt(this.height);
		out.writeUTF(this.name);
		out.writeUTF(this.obf0);
		out.writeInt(this.fp);
		out.writeInt(this.background);
		out.writeInt(this.obf1);
		out.writeBoolean(this.allowPlayerTeleport);
		out.writeBoolean(this.showDisplays);
		out.writeShort(this.clientXML.length);
		for (String xml: this.clientXML) {
			byte[] utf = xml.getBytes("UTF-8");
			out.writeInt(utf.length);
			out.write(utf);
		}
		out.writeShort(this.extraXML.length);
		for (String xml: this.extraXML) {
			byte[] utf = xml.getBytes("UTF-8");
			out.writeInt(utf.length);
			out.write(utf);
		}
	}

}
