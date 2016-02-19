package es.manning.batchlayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import backtype.cascading.tap.PailTap;
import backtype.hadoop.pail.Pail;
import cascalog.ops.IdentityBuffer;
import cascalog.ops.RandLong;
import es.manning.tap.DataPailTap;
import jcascalog.Api;
import jcascalog.Subquery;

public class Ingestor {

	public Ingestor() {
		System.out.println("entra");
		setApplicationConf();
	}

	// ingest new data to master data set
	@SuppressWarnings("rawtypes")
	public static void ingest(Pail masterPail, Pail newDataPail) throws IOException {
		FileSystem fs = FileSystem.get(new Configuration());
		fs.delete(new Path("/tmp/swa"), true);
		// tmp/swa is used as a temporary workspace throughout the batch
		// workflow.
		fs.mkdirs(new Path("/tmp/swa"));

		// Takes a snapshot of the new-data pail
		Pail snapshotPail = newDataPail.snapshot("/tmp/swa/newDataSnapshot");
		
		// Appends data from the snapshot to the master dataset
		appendNewDataToMasterDataPail(masterPail, snapshotPail);
		
		// After the append, deletes only the data that exists in the snapshot
		newDataPail.deleteSnapshot(snapshotPail);
	}

	// shred the new data and the master data set absorb the result of the
	// shreding
	@SuppressWarnings("rawtypes")
	public static void appendNewDataToMasterDataPail(Pail masterPail, Pail snapshotPail) throws IOException {
		Pail shreddedPail = shred();
		masterPail.absorb(shreddedPail);

	}

	// takes the data from de snapshot a create PailTap (DataPailStructure and
	// SplitDataPailStructure)
	// to build the Vertical Partitioning, this is to manage a large amount of
	// files
	@SuppressWarnings("rawtypes")
	public static Pail shred() throws IOException {
		
		PailTap source = DataPailTap.dataTap("/tmp/swa/newDataSnapshot");
		PailTap sink = DataPailTap.splitDataTap("/tmp/swa/shredded");

		//The tap emits the file containing the record and the record itself;
		//the filename isn’t needed in the workflow, and so can be ignored.
		Subquery reduced = new Subquery("?rand", "?data")
				.predicate(source, "_", "?data-in")
				.predicate(new RandLong(), "?rand")
				.predicate(new IdentityBuffer(), "?data-in")
					.out("?data");

		Api.execute(sink, new Subquery("?data").predicate(reduced, "_", "?data"));

		Pail shreddedPail = new Pail("/tmp/swa/shredded");
		shreddedPail.consolidate();
		return shreddedPail;
	}

	// config of serializer and deserializer
	public static void setApplicationConf() {
		Map<String, String> conf = new HashMap<String, String>();
		// The Thrift serializer for SuperWebAnalytics.com objects
		String sers = "backtype.hadoop.ThriftSerialization";
		sers += ",";
		// The default serializer for Hadoop Writable objects
		sers += "org.apache.hadoop.io.serializer.WritableSerialization";
		conf.put("io.serializations", sers);
		Api.setApplicationConf(conf);
	}

}
