package pl.asie.computronics.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pl.asie.computronics.Computronics;
import pl.asie.computronics.item.block.IBlockWithSpecialText;
import pl.asie.computronics.reference.Config;
import pl.asie.computronics.tile.TileChatBox;
import pl.asie.computronics.util.StringUtil;

import java.util.List;

public class BlockChatBox extends BlockMachineSidedIcon implements IBlockWithSpecialText {
	private IIcon mSide;

	public BlockChatBox() {
		super();
		this.setCreativeTab(Computronics.tab);
		this.setIconName("computronics:chatbox");
		this.setBlockName("computronics.chatBox");
	}

	// I'm such a cheater.
	@Override
	public int getRenderColor(int meta) {
		return meta >= 8 ? 0xFF60FF : 0xFFFFFF;
	}

	// Cheaters never win! ~ jaquadro
	@Override
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
		return getRenderColor(blockAccess.getBlockMetadata(x, y, z));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileChatBox();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List blockList) {
		blockList.add(new ItemStack(item, 1, 0));
		if(Config.CHATBOX_CREATIVE) {
			blockList.add(new ItemStack(item, 1, 8));
		}
	}

	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getAbsoluteSideIcon(int sideNumber, int metadata) {
		return mSide;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		super.registerBlockIcons(r);
		mSide = r.registerIcon("computronics:chatbox_side");
	}

	@Override
	public boolean emitsRedstone(IBlockAccess world, int x, int y, int z, int side) {
		return Config.REDSTONE_REFRESH;
	}

	@Override
	public boolean hasSubTypes() {
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean wat) {
		if(stack.getItemDamage() >= 8) {
			list.add(EnumChatFormatting.GRAY + StringUtil.localize("tooltip.computronics.chatBox.creative"));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return (stack.getItemDamage() >= 8 ? "tile.computronics.chatBox.creative" : this.getUnlocalizedName());
	}
}
