package network.webtech.labmvc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import network.webtech.labmvc.models.Asset;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @GetMapping
    public ResponseEntity getAssets() {
        Asset asset = new Asset ();
        asset.setId("1");
        asset.setTicker("AAPL");
        asset.setDescription("Apple Inc.");
        return ResponseEntity.ok(asset);
    }
}
