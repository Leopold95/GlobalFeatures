package me.leopold95.funutils.core.oregeneration;

import org.bukkit.Material;

public class SimpleBlockInfo {
	public SimpleBlockInfo(Vector3 localPos, Material material){
		this.localPos = localPos;
		this.material = material;
	}

	public Vector3 localPos;
	public Material material;
}
