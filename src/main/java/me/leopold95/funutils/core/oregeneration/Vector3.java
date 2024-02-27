package me.leopold95.funutils.core.oregeneration;

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
}
