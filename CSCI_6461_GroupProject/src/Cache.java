import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Cache {
	//queue contains the cache line address
	private Queue<Integer> cacheAddress;
	//map contains the cache line data
	private Map<Integer, CacheLine> cacheData;
	private static final int BLOCK_SIZE = 16;
	
	
	public Cache() {
		cacheAddress = new ArrayDeque<Integer>(BLOCK_SIZE);
		cacheData = new HashMap<Integer, CacheLine>(BLOCK_SIZE);
	}
	
	public String read(int address) {
		int offset = isHit(address);
		//cache hit, return data copy from cache
		if (offset != -1) {
			System.out.print("found");
			CacheLine cl = cacheData.get(address-offset);
			Word data = cl.getData(offset);
			return data.convertToString();
		}
		else {
			System.out.print("not found");
			CacheLine newCL = new CacheLine();
			Word data = newCL.updateCacheLine(address);
			replace(address, newCL);
			return data.convertToString();
		}
	}
	
	public void writeThrough(int address, String data) {
		int offset = isHit(address);
		//cache hit, write to cache and memory
		if (offset != -1) {
			System.out.print("found");
			cacheData.get(address-offset).writeToData(offset, data);
			MainApp.myMemory.writeToMemory(address, data);
		}
		//cache miss, replace cache
		else {
			System.out.print("Not found");
			CacheLine newCL = new CacheLine();
			newCL.updateCacheLine(address);
			replace(address, newCL);
			writeThrough(address, data);
		}
	}
	
	public void writeBack(int address, String data) {
		int offset = isHit(address);
		//cache hit, only write to cache
		if (offset != -1) {
			System.out.print("found");
			cacheData.get(address-offset).writeToData(offset, data);
		}
		//cache miss, replace cache and write to memory
		else {
			System.out.print("Not found");
			CacheLine newCL = new CacheLine();
			newCL.updateCacheLine(address);
			replaceAndUpdate(address, newCL);
			writeBack(address, data);
		}
	}
	
	private int isHit(int address) {
		if (cacheAddress.contains(address)) return 0;
		else if (cacheAddress.contains(address-1)) return 1;
		else if (cacheAddress.contains(address-2)) return 2;
		else if (cacheAddress.contains(address-3)) return 3;
		return -1;
	}
	
	private void replace(int newAddr, CacheLine newCL) {
		//map not yet full, don't need to replace
		if (cacheAddress.size() < BLOCK_SIZE) {
		}
		//full, replace by FIFO
		else {
			int oldAddr = cacheAddress.poll();
			if (cacheData.containsKey(oldAddr)) cacheData.remove(oldAddr);
		}
		cacheAddress.add(newAddr);
		cacheData.put(newAddr, newCL);
	}
	
	private void replaceAndUpdate(int newAddr, CacheLine newCL) {
		//map not yet full, don't need to replace
		if (cacheAddress.size() < BLOCK_SIZE) {
			System.out.println("in");
		}
		//full, replace by FIFO and write to lower level
		else {
			int oldAddr = cacheAddress.poll();
			if (cacheData.containsKey(oldAddr)) {
				CacheLine oldBlock = cacheData.get(oldAddr);
				for (int i = 0; i < oldBlock.getBlock().size(); i++) {
					MainApp.myMemory.writeToMemory(oldAddr+i, oldBlock.getBlock().get(i).convertToString());
				}
			}
		}
		cacheAddress.add(newAddr);
		cacheData.put(newAddr, newCL);
	}
}
