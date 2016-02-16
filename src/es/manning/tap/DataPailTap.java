package es.manning.tap;

import backtype.cascading.tap.PailTap;
import backtype.cascading.tap.PailTap.PailTapOptions;
import backtype.hadoop.pail.PailSpec;
import backtype.hadoop.pail.PailStructure;

public  class DataPailTap {
	
	//create a PailTap with a DataPailStructure
	@SuppressWarnings("rawtypes")
	public static PailTap dataTap(String path) {
        PailTapOptions opts = new PailTapOptions();
        opts.spec = new PailSpec(
                      (PailStructure) new DataPailStructure());
        return new PailTap(path, opts);
    }
	
	//create a PailTap with a SplitDataPailStructure
	@SuppressWarnings("rawtypes")
	public static PailTap splitDataTap(String path) {
        PailTapOptions opts = new PailTapOptions();
        opts.spec = new PailSpec(
                      (PailStructure) new SplitDataPailStructure());
        return new PailTap(path, opts);
    }

}
