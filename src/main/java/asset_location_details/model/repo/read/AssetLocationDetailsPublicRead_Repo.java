package asset_location_details.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import asset_location_details.model.master.AssetLocationDetail;
import asset_location_details.model.master.AssetLocationDetailPK;

@Repository("assetLocationDetailsPublicReadRepo")
public interface AssetLocationDetailsPublicRead_Repo extends JpaRepository<AssetLocationDetail, AssetLocationDetailPK> 
{

	@Query(value = "SELECT * FROM ASSET_Location_DETAILS order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetLocationDetail> getAllAssetLocationsDetails();
	
	@Query(value = "SELECT * FROM ASSET_Location_DETAILS a WHERE a.asset_seq_no in :ids  order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetLocationDetail> getSelectAssetLocationsDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM ASSET_Location_DETAILS a WHERE ((:fr BETWEEN FR_DTTM and TO_DTTM) and (:to BETWEEN FR_DTTM and TO_DTTM))  order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetLocationDetail> getDetailsBetweenTimes(@Param("fr") Timestamp fr, @Param("to") Timestamp to);
	
}