package asset_meter_details.model.repo.cud;

import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import asset_meter_details.model.master.AssetMeterDetail;
import asset_meter_details.model.master.AssetMeterDetailPK;

@Repository("assetMeterDetailsPublicCUDRepo")
public interface AssetMeterDetailsPublicCUD_Repo extends JpaRepository<AssetMeterDetail, AssetMeterDetailPK> 
{

	@Query(value = "delete FROM ASSET_meter_DETAILS a WHERE ON_DTTM >= :fr and ON_DTTM <= :to", nativeQuery = true)
	void delMeterDetailsBetweenTimes(@Param("fr") Timestamp fr, @Param("to") Timestamp to);

}