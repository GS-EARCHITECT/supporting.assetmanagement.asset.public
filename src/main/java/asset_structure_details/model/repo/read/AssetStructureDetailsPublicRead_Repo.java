package asset_structure_details.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import asset_structure_details.model.master.AssetStructureDetail;
import asset_structure_details.model.master.AssetStructureDetailPK;

@Repository("assetStructureDetailsPublicReadRepo")
public interface AssetStructureDetailsPublicRead_Repo extends JpaRepository<AssetStructureDetail, AssetStructureDetailPK> 
{

	@Query(value = "SELECT * FROM ASSET_Structure_DETAILS order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetStructureDetail> getAllAssetStructuresDetails();
	
	@Query(value = "SELECT * FROM ASSET_Structure_DETAILS a WHERE a.asset_seq_no in :ids  order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetStructureDetail> getSelectAssetStructuresDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM ASSET_Structure_DETAILS a WHERE ((:fr BETWEEN FR_DTTM and TO_DTTM) and (:to BETWEEN FR_DTTM and TO_DTTM))  order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetStructureDetail> getDetailsBetweenTimes(@Param("fr") Timestamp fr, @Param("to") Timestamp to);
	
}