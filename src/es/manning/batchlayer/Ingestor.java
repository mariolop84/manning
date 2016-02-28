package es.manning.batchlayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


import com.backtype.cascading.tap.PailTap;
import com.backtype.hadoop.pail.Pail;
import cascalog.ops.IdentityBuffer;
import cascalog.ops.RandLong;
import es.manning.tap.DataPailTap;
import jcascalog.Api;
import jcascalog.Subquery;

public class Ingestor {
	

	public Ingestor() {
		System.out.println("Ingestor.Ingestor: INICIO");
		setApplicationConf();
		System.out.println("Ingestor.Ingestor: FIN");
	}

	//Ingest new data to master data set
	@SuppressWarnings("rawtypes")
	public void ingest(Pail masterPail, Pail newDataPail) throws IOException {
		System.out.println("Ingestor.ingest: INICIO");
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
		System.out.println("Ingestor.ingest: INICIO");
	}

	//Shred the new data then the masterdata set absorb the result of the shreding
	@SuppressWarnings("rawtypes")
	public static void appendNewDataToMasterDataPail(Pail masterPail, Pail snapshotPail) throws IOException {
		Pail shreddedPail = shred();
		masterPail.absorb(shreddedPail);

	}

	//Takes the data from a snapshot and create two PailTaps (DataPailStructure and SplitDataPailStructure)
	//this is for the Vertical Partitioning, and manage a large amount of files
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
		System.out.println("Ingestor.setApplicationConf: INICIO");
		Map<String, String> conf = new HashMap<String, String>();
		// The Thrift serializer for SuperWebAnalytics.com objects
		String sers = "backtype.hadoop.ThriftSerialization";
		sers += ",";
		// The default serializer for Hadoop Writable objects
		sers += "org.apache.hadoop.io.serializer.WritableSerialization";
		conf.put("io.serializations", sers);
		Api.setApplicationConf(conf);
		System.out.println("Ingestor.setApplicationConf: FIN");
	}

}
