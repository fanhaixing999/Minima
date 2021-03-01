package org.minima.kissvm.functions.maths;

import java.util.BitSet;

import org.minima.kissvm.Contract;
import org.minima.kissvm.exceptions.ExecutionException;
import org.minima.kissvm.functions.MinimaFunction;
import org.minima.kissvm.values.BooleanValue;
import org.minima.kissvm.values.Value;
import org.minima.objects.base.MiniData;

public class BITGET extends MinimaFunction {

	/**
	 * @param zName
	 */
	public BITGET() {
		super("BITGET");
	}
	
	/* (non-Javadoc)
	 * @see org.ramcash.ramscript.functions.Function#runFunction()
	 */
	@Override
	public Value runFunction(Contract zContract) throws ExecutionException {
		checkExactParamNumber(2);
		
		//get the Input Data
		byte[] data = zContract.getHEXParam(0, this).getRawData();
		int totbits = (data.length * 8) - 1;
		
		//Get the desired Bit
		int bit = zContract.getNumberParam(1, this).getNumber().getAsInt();
		if(bit>totbits) {
			throw new ExecutionException("BitGet too large "+bit+" / "+totbits);
		}
		
		//Create a BitSet object..
		BitSet bits = BitSet.valueOf(data);
		
		//Now check the value..
		boolean isSet = bits.get(bit);
		
		//return..
		return new BooleanValue(isSet);
	}
	
	@Override
	public MinimaFunction getNewFunction() {
		return new BITGET();
	}
}
