package chapter4;

import chapter4.Asset.AssetType;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class AssetUtil {

  //uses lambdas, but still has separation of concern issue
  public static int totalAssetValues(final List<Asset> assets) {
    return assets.stream().mapToInt(Asset::getValue).sum();
  }
  
  //this way lets us pass the predicate
  public static int totalAssetValues(final List<Asset> assets, 
      final Predicate<Asset> assetSelector) {
    return assets.stream()
        .filter(assetSelector)
        .mapToInt(Asset::getValue)
        .sum();
  }
  
  public static void main(String[] args) {
    final List<Asset> assets = Arrays.asList(new Asset(Asset.AssetType.BOND, 1000),
    new Asset(Asset.AssetType.BOND, 2000),
    new Asset(Asset.AssetType.STOCK, 3000),
    new Asset(Asset.AssetType.STOCK, 4000));
    
    //we want all assets total. Pass in a predicate that always results in true
    System.out.println("Total all assets: " + totalAssetValues(assets, asset -> true));
    //a certain type of asset total
    System.out.println("Total BOND assets: " + totalAssetValues(assets, asset -> asset.getType() == AssetType.BOND));
  }
}
