package asset_price_details.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import asset_price_details.model.master.AssetPriceDetail;
import asset_price_details.model.master.AssetPriceDetailPK;

@Repository("assetPriceDetailsPublicReadRepo")
public interface AssetPriceDetailsPublicRead_Repo extends JpaRepository<AssetPriceDetail, AssetPriceDetailPK> 
{

	@Query(value = "SELECT * FROM ASSET_Price_DETAILS order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetPriceDetail> getAllAssetPricesDetails();
	
	@Query(value = "SELECT * FROM ASSET_Price_DETAILS a WHERE a.asset_seq_no in :ids  order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetPriceDetail> getSelectAssetPricesDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM ASSET_Price_DETAILS a WHERE ((:fr BETWEEN FR_DTTM and TO_DTTM) and (:to BETWEEN FR_DTTM and TO_DTTM))  order by ASSET_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<AssetPriceDetail> getDetailsBetweenTimes(@Param("fr") Timestamp fr, @Param("to") Timestamp to);
	
}