package asset_date_details.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import asset_date_details.model.master.AssetDateDetail;
import asset_date_details.model.master.AssetDateDetailPK;

@Repository("assetDateDetailsPublicReadRepo")
public interface AssetDateDetailsPublicRead_Repo extends JpaRepository<AssetDateDetail, AssetDateDetailPK> 
{
	@Query(value = "SELECT * FROM ASSET_Date_details a order by asset_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<AssetDateDetail> getAllAssetDateDetails();
	
	@Query(value = "SELECT * FROM ASSET_Date_details a WHERE a.asset_seq_no in :ids order by asset_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<AssetDateDetail> getSelectAssetDateDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);	
}