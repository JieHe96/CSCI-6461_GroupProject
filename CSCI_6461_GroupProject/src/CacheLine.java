import java.util.Vector;

public class CacheLine {
	private Vector<Word> block;
	private static final int LINE_SIZE = 4;
	
	public CacheLine() {
		block = new Vector<Word>(LINE_SIZE);
	}
	
	public Word updateCacheLine(int startAddr) {
		int offset = startAddr;
		for (int i = 0; i < LINE_SIZE; i++) {
			if (offset < 2048) {
				Word temp = MainApp.myMemory.readFromMemory(offset);
				block.add(temp);
				offset++;
			}
		}
		return MainApp.myMemory.readFromMemory(startAddr);
	}
	
	public Word getData(int offset) {
		return block.get(offset);
	}
	
	public void writeToData(int offset, String value) {
		block.get(offset).write(value);
	}
}
