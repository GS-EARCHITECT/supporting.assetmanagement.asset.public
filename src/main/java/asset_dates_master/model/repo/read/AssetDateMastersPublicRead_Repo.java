package asset_dates_master.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import asset_dates_master.model.master.AssetDateMaster;

@Repository("assetDateMastersPublicReadRepo")
public interface AssetDateMastersPublicRead_Repo extends JpaRepository<AssetDateMaster, Long> 
{
	@Query(value = "SELECT * FROM ASSET_Date_MASTER a order by date_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<AssetDateMaster> getAllAssetDateMasters();
	
	@Query(value = "SELECT * FROM ASSET_Date_MASTER a WHERE a.date_seq_no in :ids order by date_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<AssetDateMaster> getSelectAssetDateMasters(@Param("ids") CopyOnWriteArrayList<Long> ids);
		
}