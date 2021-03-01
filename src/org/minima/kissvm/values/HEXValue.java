package org.minima.kissvm.values;

import java.math.BigInteger;

import org.minima.objects.base.MiniData;
import org.minima.objects.base.MiniNumber;

public class HEXValue extends Value {
	
	/**
	 * The RAW bytes
	 */
	protected MiniData mData;
	
	/**
	 * Needed by ScriptValue to init
	 */
	protected HEXValue() {}
	
	/**
	 * Convert a MiniData byte array into a HEXValue
	 * 
	 * @param zData
	 */
	public HEXValue(MiniData zData) {
		this(zData.getData()); 
	}
	
	/**
	 * Convert a byte array into a HEXValue
	 * 
	 * @param zData
	 */
	public HEXValue(byte[] zData) {
		//It's a HEX value..
		mData   = new MiniData(zData);
	}
	
	/**
	 * Convert a HEX String into a byte array
	 * 
	 * @param zHex
	 */
	public HEXValue(String zHex) {
		//HEX
		mData 	= new MiniData(zHex);
	}
	
	/**
	 * Convert a positive whole number into a HEXValue..
	 * 
	 * If the number is not positive or not a whole number exception thrown
	 * 
	 * @param zNumber
	 */
	public HEXValue(MiniNumber zNumber) {
		if(zNumber.isLess(MiniNumber.ZERO)){
			throw new NumberFormatException("HEXValue Number must be positive");
		}
		
		if(!zNumber.floor().isEqual(zNumber)){
			throw new NumberFormatException("HEXValue Number must be a whole number");
		}
		
		//HEX
		mData 	= new MiniData(zNumber.getAsBigInteger().toByteArray());
	}
	
	/**
	 * The Data version
	 * @return
	 */
	public MiniData getMiniData() {
		return mData;
	}
	
	/**
	 * Get the RAW byte data
	 * @return
	 */
	public byte[] getRawData() {
		return getMiniData().getData();
	}
	
	@Override
	public int getValueType() {
		return VALUE_HEX;
	}
	
	@Override
	public boolean isFalse() {
		return mData.getDataValue().equals(BigInteger.ZERO);
	}
	
	public boolean isEqual(HEXValue zValue) {
		return mData.isEqual(zValue.getMiniData());
	}
	
	public boolean isLess(HEXValue zValue) {
		return mData.isLess(zValue.getMiniData());
	}
	
	public boolean isLessEqual(HEXValue zValue) {
		return mData.isLessEqual(zValue.getMiniData());
	}
	
	public boolean isMore(HEXValue zValue) {
		return mData.isMore(zValue.getMiniData());
	}
	
	public boolean isMoreEqual(HEXValue zValue) {
		return mData.isMoreEqual(zValue.getMiniData());
	}
	
	@Override
	public String toString() {
		return mData.toString();
	}
}
