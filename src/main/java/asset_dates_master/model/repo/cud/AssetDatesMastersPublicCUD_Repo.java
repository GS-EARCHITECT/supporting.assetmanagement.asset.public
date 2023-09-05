package asset_dates_master.model.repo.cud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import asset_dates_master.model.master.AssetDatesMaster;

@Repository("assetDatesMastersPublicCUDRepo")
public interface AssetDatesMastersPublicCUD_Repo extends JpaRepository<AssetDatesMaster, Long> 
{}