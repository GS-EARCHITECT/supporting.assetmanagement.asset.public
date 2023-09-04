package asset_measures_details.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import asset_measures_details.model.master.AssetMeasuresDetail;
import asset_measures_details.model.master.AssetMeasuresDetailPK;

@Repository("assetMeasuresDetailsPublicReadRepo")
public interface AssetMeasuresDetailsPublicRead_Repo extends JpaRepository<AssetMeasuresDetail, AssetMeasuresDetailPK> 
{

	@Query(value = "SELECT * FROM ASSET_Measures_DETAILS order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetMeasuresDetail> getAllAssetMeasuressDetails();
	
	@Query(value = "SELECT * FROM ASSET_Measures_DETAILS a WHERE a.asset_seq_no in :ids  order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetMeasuresDetail> getSelectAssetMeasuressDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
}