package asset_meter_details.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import asset_meter_details.model.master.AssetMeterDetail;
import asset_meter_details.model.master.AssetMeterDetailPK;

@Repository("assetMeterDetailsPublicReadRepo")
public interface AssetMeterDetailsPublicRead_Repo extends JpaRepository<AssetMeterDetail, AssetMeterDetailPK> 
{

	@Query(value = "SELECT * FROM ASSET_meter_DETAILS a WHERE asset_seq_no in :ids order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetMeterDetail> getSelectMeterDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value = "SELECT * FROM ASSET_meter_DETAILS order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetMeterDetail> getAllMeterDetails();
	
	@Query(value = "SELECT * FROM ASSET_meter_DETAILS a WHERE ON_DTTM >= :fr and ON_DTTM <= :to  order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetMeterDetail> getMeterDetailsBetweenTimes(@Param("fr") Timestamp fr, @Param("to") Timestamp to);

	
}