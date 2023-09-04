package asset_structure_details.model.repo.cud;

import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import asset_structure_details.model.master.AssetStructureDetail;
import asset_structure_details.model.master.AssetStructureDetailPK;

@Repository("assetStructureDetailsPublicCUDRepo")
public interface AssetStructureDetailsPublicCUD_Repo extends JpaRepository<AssetStructureDetail, AssetStructureDetailPK> 
{

	@Query(value = "SELECT * FROM ASSET_Structure_DETAILS a WHERE ((:fr BETWEEN FR_DTTM and TO_DTTM) and (:to BETWEEN FR_DTTM and TO_DTTM))", nativeQuery = true)
	void delDetailsBetweenTimes(@Param("fr") Timestamp fr, @Param("to") Timestamp to);

}