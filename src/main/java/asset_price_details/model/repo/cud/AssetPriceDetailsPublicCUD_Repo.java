package asset_price_details.model.repo.cud;

import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import asset_price_details.model.master.AssetPriceDetail;
import asset_price_details.model.master.AssetPriceDetailPK;

@Repository("assetPriceDetailsPublicCUDRepo")
public interface AssetPriceDetailsPublicCUD_Repo extends JpaRepository<AssetPriceDetail, AssetPriceDetailPK> 
{

	@Query(value = "SELECT * FROM ASSET_Price_DETAILS a WHERE ((:fr BETWEEN FR_DTTM and TO_DTTM) and (:to BETWEEN FR_DTTM and TO_DTTM))", nativeQuery = true)
	void delDetailsBetweenTimes(@Param("fr") Timestamp fr, @Param("to") Timestamp to);

}