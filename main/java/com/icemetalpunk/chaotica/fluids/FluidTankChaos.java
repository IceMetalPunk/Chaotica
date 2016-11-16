package com.icemetalpunk.chaotica.fluids;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class FluidTankChaos extends FluidTank {

	public FluidTankChaos(int capacity) {
		super(capacity);
	}

	@Override
	public boolean canFillFluidType(FluidStack stack) {
		if (this.fluid == null) {
			return true;
		}
		boolean isSame = this.fluid.amount > 0 && stack.isFluidEqual(this.fluid);
		return this.canFill && (isSame || (this.fluid.amount <= 0 && (stack.getFluid() instanceof FluidChaosBase)));
	}

}
