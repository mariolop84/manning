package es.manning.tap;

import java.util.ArrayList;
import java.util.List;

import com.backtype.cascading.tap.PailTap;
import com.backtype.cascading.tap.PailTap.PailTapOptions;
import com.backtype.hadoop.pail.PailSpec;
import com.backtype.hadoop.pail.PailStructure;
import es.manning.schema.DataUnit;
import es.manning.tap.SplitDataPailStructure;

public class DataPailTap {

	// create a PailTap with a DataPailStructure
	@SuppressWarnings("rawtypes")
	public static PailTap dataTap(String path) {
		// Relays custom configurations to the PailTap
		PailTapOptions opts = new PailTapOptions();

		opts.spec = new PailSpec((PailStructure) new DataPailStructure());
		return new PailTap(path, opts);
	}

	// create a PailTap with a SplitDataPailStructure
	@SuppressWarnings("rawtypes")
	public static PailTap splitDataTap(String path) {
		PailTapOptions opts = new PailTapOptions();
		opts.spec = new PailSpec((PailStructure) new SplitDataPailStructure());
		return new PailTap(path, opts);
	}

	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public static PailTap attributeTap(String path, final DataUnit._Fields... fields) {
		// Relays custom configurations to the PailTap
		PailTapOptions opts = new PailTapOptions();
		// The attributes are an array of lists; each list contains
		// the directory path of a subfolder to be used as input.
		opts.attrs = new List[] { new ArrayList<String>() {
			{
				// Creates a list containing the
				// relative path of the equiv edges
				for (DataUnit._Fields field : fields) {
					// Multiple subfolders can be specified as
					// input to the tap.
					add("" + field.getThriftFieldId());
				}
			}
		} };
		// Creates the tap with the specified options
		opts.spec = new PailSpec((PailStructure) new SplitDataPailStructure());

		return new PailTap(path, opts);
	}

}
