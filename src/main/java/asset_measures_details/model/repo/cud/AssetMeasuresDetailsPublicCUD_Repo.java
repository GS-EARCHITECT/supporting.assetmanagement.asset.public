package asset_measures_details.model.repo.cud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import asset_measures_details.model.master.AssetMeasuresDetail;
import asset_measures_details.model.master.AssetMeasuresDetailPK;

@Repository("assetMeasuresDetailsPublicCUDRepo")
public interface AssetMeasuresDetailsPublicCUD_Repo extends JpaRepository<AssetMeasuresDetail, AssetMeasuresDetailPK> 
{}