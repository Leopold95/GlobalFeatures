package me.leopold95.globalfeatures.core.oregeneration;

/**
 * Простая реализация контрейра координат
 */
public class Vector3 {
	int x = 0;
	int y = 0;
	int z = 0;

	public Vector3(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return "Vector3 [" + x + ", "+ y +", "+ z +"]";
	}

	public Vector3 addAndCopy(int x, int y, int z){
		return new Vector3(this.x + x, this.y + y, this.z + z);
	}

	public Vector3 addAndCopy(int value){
		return new Vector3(this.x + value, this.y + value, this.z + value);
	}

	public void add(int x, int y, int z){
		this.x += x;
		this.y += y;
		this.z += z;
	}
}