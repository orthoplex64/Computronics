package pl.asie.computronics.block;

import cpw.mods.fml.common.Optional;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pl.asie.computronics.Computronics;
import pl.asie.computronics.reference.Mods;
import pl.asie.lib.block.BlockBase;

public abstract class BlockPeripheral extends BlockBase {

	public BlockPeripheral() {
		super(Material.iron, Computronics.instance);
		this.setCreativeTab(Computronics.tab);
	}

	@Override
	@Optional.Method(modid = Mods.NedoComputers)
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int a, float _x, float _y, float _z) {
		if (!world.isRemote && player.isSneaking()) {
			try {
				Object ncinst = this.getClass().getClassLoader().loadClass("nedocomputers.NedoComputers").getField("instance").get(null);
				player.openGui(ncinst, 2, world, x, y, z);
				return true;
			} catch(Exception e) { e.printStackTrace(); }
		}
		return super.onBlockActivated(world, x, y, z, player, a, _x, _y, _z);
	}

	@Override
	public boolean isOpaqueCube() {
		return true;
	}

	@Override
	public boolean isNormalCube() {
		return true;
	}

	@Override
	public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
		return true;
	}
}
