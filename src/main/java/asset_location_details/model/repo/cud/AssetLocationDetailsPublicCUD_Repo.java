package asset_location_details.model.repo.cud;

import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import asset_location_details.model.master.AssetLocationDetail;
import asset_location_details.model.master.AssetLocationDetailPK;

@Repository("assetLocationDetailsPublicCUDRepo")
public interface AssetLocationDetailsPublicCUD_Repo extends JpaRepository<AssetLocationDetail, AssetLocationDetailPK> 
{

	@Query(value = "SELECT * FROM ASSET_Location_DETAILS a WHERE ((:fr BETWEEN FR_DTTM and TO_DTTM) and (:to BETWEEN FR_DTTM and TO_DTTM))", nativeQuery = true)
	void delDetailsBetweenTimes(@Param("fr") Timestamp fr, @Param("to") Timestamp to);

}